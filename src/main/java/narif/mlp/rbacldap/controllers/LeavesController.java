package narif.mlp.rbacldap.controllers;

import narif.mlp.rbacldap.model.Leave;
import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.repositories.UserJpaRepo;
import narif.mlp.rbacldap.services.LeaveService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("leaves")
public class LeavesController {

    private final LeaveService leaveService;
    private final UserJpaRepo userJpaRepo;

    public LeavesController(LeaveService leaveService, UserJpaRepo userJpaRepo) {
        this.leaveService = leaveService;
        this.userJpaRepo = userJpaRepo;
    }

    @PostMapping
    public Leave createLeave(@RequestBody @Valid Leave leave, Authentication authentication) {
        final var userOptional = userJpaRepo.findByEmailId(authentication.getName());
        userOptional.ifPresent(leave::setUser);
        return leaveService.createLeave(leave);
    }

    @GetMapping
    public List<Leave> getAllMyLeaves(Authentication authentication) {
        final var name = authentication.getName();
        return userJpaRepo.findByEmailId(name)
                .map(leaveService::getAllMyLeaves)
                .orElse(Collections.emptyList());
    }
}
