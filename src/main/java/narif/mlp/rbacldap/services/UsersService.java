package narif.mlp.rbacldap.services;

import narif.mlp.rbacldap.exceptions.UserAlreadyRegisteredException;
import narif.mlp.rbacldap.model.LdapUser;
import narif.mlp.rbacldap.model.PasswordDto;
import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.repositories.LdapUserRepository;
import narif.mlp.rbacldap.repositories.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UsersService {

    private final UserJpaRepo userJpaRepo;
    private final LdapUserRepository ldapUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService(UserJpaRepo userJpaRepo, LdapUserRepository ldapUserRepository) {
        this.userJpaRepo = userJpaRepo;
        this.ldapUserRepository = ldapUserRepository;
    }

    Boolean isUserRegistered(String email) {
        return userJpaRepo.findByEmailId(email).isPresent();
    }

    public User createUser(User user) {
        if(isUserRegistered(user.getEmailId())){
            throw new UserAlreadyRegisteredException();
        }
        final User savedUser = saveUserToDb(user);
        saveUserToLdap(savedUser);
        return savedUser;
    }

    private void saveUserToLdap(User savedUser) {
        final var ldapUser = savedUser.getLdapUser();
        ldapUserRepository.save(ldapUser);
    }

    private User saveUserToDb(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        final var savedUser = userJpaRepo.save(user);
        return savedUser;
    }

    public Boolean updatePassword(String email, PasswordDto passwordDto) {
        final var userOptional = userJpaRepo.findByEmailId(email);
        userOptional.ifPresent(updatePassword(passwordDto));
        return true;
    }

    private Consumer<User> updatePassword(PasswordDto passwordDto) {
        return user -> {
            if (user.getPassword().equals(bCryptPasswordEncoder.encode(passwordDto.getOldPassword()))) {
                user.setPassword(bCryptPasswordEncoder.encode(passwordDto.getNewPassword()));
                userJpaRepo.save(user);
                ldapUserRepository.save(user.getLdapUser());
            } else {
                throw new RuntimeException("invalid current password");
            }
        };
    }
}
