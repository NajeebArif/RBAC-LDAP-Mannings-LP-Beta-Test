package narif.mlp.rbacldap.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("login")
@Slf4j
public class LoginController {

    @PostMapping
    public ResponseEntity<Boolean> isLoginAllowed(HttpServletRequest request, Authentication authentication) {
        final var authorization = request.getHeader("Authorization");
        if(null == authorization || authorization.trim().equals(""))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        log.info("Login attempt for user email {}",authentication.getName());
        return ResponseEntity.ok(true);
    }
}
