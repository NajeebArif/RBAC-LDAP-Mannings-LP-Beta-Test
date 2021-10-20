package narif.mlp.rbacldap.controllers;

import narif.mlp.rbacldap.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping
    public List<User> getAllUsers(){
        return Collections.singletonList(new User());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return user;
    }
}
