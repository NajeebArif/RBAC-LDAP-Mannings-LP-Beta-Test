package narif.mlp.rbacldap.services;

import narif.mlp.rbacldap.model.Leave;
import narif.mlp.rbacldap.repositories.LeaveRepository;
import org.springframework.stereotype.Service;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public Leave createLeave(final Leave leave){
        return leaveRepository.save(leave);
    }
}
