package narif.mlp.rbacldap.controllers;

import narif.mlp.rbacldap.model.Leave;
import narif.mlp.rbacldap.services.LeaveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("leaves")
public class LeavesController {

    private final LeaveService leaveService;

    public LeavesController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public Leave createLeave(@RequestBody @Valid Leave leave){
        return leaveService.createLeave(leave);
    }
}
