package narif.mlp.rbacldap.controllers;

import narif.mlp.rbacldap.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Users Controller Specs:")
class UsersControllerTest {

    UsersController usersController;

    @BeforeEach
    public void init(){
        usersController = new UsersController();
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