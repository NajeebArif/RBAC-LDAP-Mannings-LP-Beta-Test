package narif.mlp.rbacldap.controllers;

import narif.mlp.rbacldap.model.PasswordDto;
import narif.mlp.rbacldap.services.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("changePassword")
public class PasswordController {

    private final UsersService usersService;

    public PasswordController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public String changePassword(@RequestBody PasswordDto passwordDto, Authentication authentication){
        final var email = authentication.getName();
        final var aBoolean = usersService.updatePassword(email, passwordDto);
        return aBoolean ? "Password updated successfully": "password update failed";
    }
}
