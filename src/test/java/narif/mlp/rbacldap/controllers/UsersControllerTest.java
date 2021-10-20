package narif.mlp.rbacldap.controllers;

import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Users Controller Specs:")
@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    @Mock
    UsersService usersServiceMock;

    UsersController usersController;

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

    @Test
    @DisplayName("Should accept User model as per API specification.")
    void createUser(){
        final var user = usersController.createUser(new User());
        assertThat(user).isNotNull().matches(u->u.getEmailId()==null);
    }
}