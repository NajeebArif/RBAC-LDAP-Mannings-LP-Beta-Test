package narif.mlp.rbacldap.model;

import lombok.Data;
import narif.mlp.rbacldap.model.validators.ValidPassword;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "User must provide a valid First Name")
    private String firstName;
    @NotBlank(message = "User must provide a valid Last Name")
    private String lastName;
    @NotBlank(message = "User should provide a value for email.")
    @Email(message = "User should provide a valid email address.")
    private String emailId;
    @Range(min = 18, max = 80)
    @NotNull
    private Integer age;
    @NotBlank
    private String phone;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;
    @ValidPassword
    private String password;
    private String managerName;
    @NotBlank
    private String address;
    private String tag;

    public LdapUser getLdapUser(){
        final var ldapUser = new LdapUser(this.emailId);
        ldapUser.setPassword(this.password);
        ldapUser.setFirstName(this.firstName);
        ldapUser.setLastName(this.lastName);
        return ldapUser;
    }
}
