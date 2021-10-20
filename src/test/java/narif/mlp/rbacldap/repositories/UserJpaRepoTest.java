package narif.mlp.rbacldap.repositories;

import narif.mlp.rbacldap.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@EnableJpaRepositories
@DisplayName("User JPA Repository Specs:")
class UserJpaRepoTest {

    @Autowired
    private UserJpaRepo userJpaRepo;

    @Test
    @DisplayName("Should return an empty optional if user is not present in the db for find by email id.")
    void testFindByEmailId(){
        final var byEmailId = userJpaRepo.findByEmailId("email@email.com");
        assertThat(byEmailId).isEmpty();
    }

    @Test
    @DisplayName("Should save the given in the db and return the saved user.")
    void testSave(){
        final var user = new User();
        user.setEmailId("John@email.com");
        final var savedUser = userJpaRepo.save(user);
        assertThat(savedUser).isNotNull().matches(su->su.getEmailId().equals(user.getEmailId()));
    }

    @Test
    @DisplayName("should save the user in the DB and find by email id should not return empty.")
    void testSaveAndFindByEmailId(){
        final var user = new User();
        user.setEmailId("John@email.com");
        final var savedUser = userJpaRepo.save(user);
        final var byEmailId = userJpaRepo.findByEmailId("John@email.com");
        assertThat(byEmailId).isNotEmpty().hasValue(user);
    }
}