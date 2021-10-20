package narif.mlp.rbacldap.repositories;

import narif.mlp.rbacldap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmailId(String userName);
}
