package narif.mlp.rbacldap.services;

import narif.mlp.rbacldap.model.Leave;
import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.repositories.LeaveRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public Leave createLeave(final Leave leave){
        return leaveRepository.save(leave);
    }

    public List<Leave> getAllMyLeaves(User user) {
        return leaveRepository.findAllByUser(user);
    }

//    @Secured("ROLE_ADMIN")
    public List<Leave> getAllLeaves(){
        return leaveRepository.findAll();
    }
}
