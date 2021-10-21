package narif.mlp.rbacldap.model;

import lombok.Data;
import org.springframework.data.domain.Persistable;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.naming.Name;
import javax.naming.ldap.LdapName;

@Entry(base = "ou=people",objectClasses = {"top", "person", "organizationalPerson", "inetOrgPerson"})
@Data
public final class LdapUser implements Persistable {

    public static final String BASE = "dc=springframework,dc=org";

    @Id
    private Name dn;

    @Attribute(name = "uid")
    private String emailId;
    @Attribute(name = "cn")
    private String firstName;
    @Attribute(name = "sn")
    private String lastName;
    @Attribute(name = "userPassword")
    private String password;
    @Transient
    private boolean isNew;

    public LdapUser() {
    }

    public LdapUser(String emailId) {
        this.dn = getDn(emailId);
        this.emailId = emailId;
        this.isNew = true;
    }

    public LdapName getDn(String emailId) {
        return LdapNameBuilder.newInstance(BASE)
                .add("ou", "people")
                .add("uid", emailId)
                .build();
    }

    @Override
    public Object getId() {
        return this.dn;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }
}
