package narif.mlp.rbacldap.services;

import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.repositories.UserJpaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("User Service Specs:")
@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    UserJpaRepo userJpaRepoMock;

    UsersService usersService;

    @BeforeEach
    public void init(){
        usersService = new UsersService(userJpaRepoMock);
    }

    @Test
    @DisplayName("should return false if a given email is not present in the db.")
    void testEmailNotPresent(){
        when(userJpaRepoMock.findByEmailId("email@email.com")).thenReturn(Optional.empty());
        final var userRegistered = usersService.isUserRegistered("email@email.com");
        assertThat(userRegistered).isNotNull().isFalse();
    }

    @Test
    @DisplayName("Should return true if user is registered.")
    void testIsRegistered(){
        when(userJpaRepoMock.findByEmailId("john@email.com")).thenReturn(Optional.of(new User()));
        assertThat(usersService.isUserRegistered("john@email.com")).isTrue();
    }

}