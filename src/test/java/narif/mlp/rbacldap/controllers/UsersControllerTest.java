package narif.mlp.rbacldap.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UsersControllerTest {

    UsersController usersController;

    @BeforeEach
    public void init(){
        usersController = new UsersController();
    }

    @Test
    void getAllUsers() {
        final var allUsers = usersController.getAllUsers();
        assertThat(allUsers).isNotNull().isNotEmpty();
    }
}