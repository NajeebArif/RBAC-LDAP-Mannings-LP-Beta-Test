package narif.mlp.rbacldap.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @PostMapping
    public ResponseEntity<Boolean> isLoginAllowed() {
        return ResponseEntity.ok(true);
    }
}
