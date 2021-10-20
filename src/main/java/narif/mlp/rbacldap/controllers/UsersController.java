package narif.mlp.rbacldap.controllers;

import narif.mlp.rbacldap.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping
    public List<User> getAllUsers(){
        return Collections.emptyList();
    }
}
