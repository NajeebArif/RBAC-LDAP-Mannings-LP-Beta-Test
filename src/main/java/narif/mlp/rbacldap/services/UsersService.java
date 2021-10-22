package narif.mlp.rbacldap.services;

import narif.mlp.rbacldap.exceptions.UserAlreadyRegisteredException;
import narif.mlp.rbacldap.model.LdapUser;
import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.repositories.LdapUserRepository;
import narif.mlp.rbacldap.repositories.UserJpaRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UserJpaRepo userJpaRepo;
    private final LdapUserRepository ldapUserRepository;

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
        final var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user);
        final var savedUser = userJpaRepo.save(user);
        return savedUser;
    }
}
