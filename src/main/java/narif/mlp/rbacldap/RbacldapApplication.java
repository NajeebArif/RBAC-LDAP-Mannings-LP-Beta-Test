package narif.mlp.rbacldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@SpringBootApplication
@EnableLdapRepositories
public class RbacldapApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacldapApplication.class, args);
	}

}
