package narif.mlp.rbacldap.controllers;

import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.services.UsersService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return Collections.singletonList(new User());
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        return usersService.createUser(user);
    }
}
