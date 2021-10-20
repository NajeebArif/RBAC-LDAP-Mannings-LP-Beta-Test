package narif.mlp.rbacldap.services;

import narif.mlp.rbacldap.exceptions.UserAlreadyRegisteredException;
import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.repositories.UserJpaRepo;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UserJpaRepo userJpaRepo;

    public UsersService(UserJpaRepo userJpaRepo) {
        this.userJpaRepo = userJpaRepo;
    }

    Boolean isUserRegistered(String email) {
        return userJpaRepo.findByEmailId(email).isPresent();
    }

    public User createUser(User user) {
        if(isUserRegistered(user.getEmailId())){
            throw new UserAlreadyRegisteredException();
        }
        final var savedUser = userJpaRepo.save(user);
        return savedUser;
    }
}
