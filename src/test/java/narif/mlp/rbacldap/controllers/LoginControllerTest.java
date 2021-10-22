package narif.mlp.rbacldap.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("Login Controller Specs:")
@WebMvcTest
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    @DisplayName("Should be logged in by ldap if ")
    void setSession(){

    }
}