package narif.mlp.rbacldap.repositories;

import narif.mlp.rbacldap.model.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LdapUserRepository extends LdapRepository<LdapUser> {

    Optional<LdapUser> findByEmailId(String emailId);
}
