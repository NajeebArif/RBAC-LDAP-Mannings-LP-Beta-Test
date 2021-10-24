package narif.mlp.rbacldap.repositories;

import narif.mlp.rbacldap.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findAllByUser(Integer userId);
}
