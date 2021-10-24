package narif.mlp.rbacldap.controllers;

import lombok.extern.slf4j.Slf4j;
import narif.mlp.rbacldap.model.Leave;
import narif.mlp.rbacldap.services.LeaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
@Slf4j
public class AdminController {

    private final LeaveService leaveService;

    public AdminController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("leaves")
    public List<Leave> fetchAllLeaves(){
        return leaveService.getAllLeaves();
    }
}
