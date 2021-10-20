package narif.mlp.rbacldap.controllers;

import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Users Controller Specs:")
@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    @Mock
    UsersService usersServiceMock;

    UsersController usersController;
    User mockedUser;

    @BeforeEach
    public void init(){
        usersController = new UsersController(usersServiceMock);
    }

    @Test
    @DisplayName("Should return all the users.")
    void getAllUsers() {
        final var allUsers = usersController.getAllUsers();
        assertThat(allUsers).isNotNull().isNotEmpty();
    }

    @Nested
    @DisplayName("Create User API Specs:")
    class CreateUserApiTests{

        @BeforeEach
        public void init(){
            mockedUser = new User();
            mockedUser.setEmailId("john@email.com");
            when(usersServiceMock.createUser(mockedUser)).thenReturn(mockedUser);
        }

        @Test
        @DisplayName("Should pass the user model to user service to create user.")
        void createUserWithService(){
            final var createdUser = usersController.createUser(mockedUser);
            assertThat(createdUser).isNotNull().matches(u->u.getEmailId().equals("john@email.com"));
            verify(usersServiceMock).createUser(mockedUser);
        }

        @Test
        @DisplayName("Should accept User model as per API specification.")
        void createUser(){
            final var user = usersController.createUser(mockedUser);
            assertThat(user).isNotNull().matches(u->u.getEmailId().equals("john@email.com"));
        }
    }


}