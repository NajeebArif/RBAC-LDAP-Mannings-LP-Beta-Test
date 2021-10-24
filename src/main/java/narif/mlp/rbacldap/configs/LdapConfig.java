package narif.mlp.rbacldap.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import static narif.mlp.rbacldap.model.LdapUser.BASE;

@Configuration
public class LdapConfig {

    @Bean
    public ContextSource contextSource(){
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://localhost:8389");
        contextSource.setBase(BASE);
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(ContextSource contextSource){
        return new LdapTemplate(contextSource);
    }
}
