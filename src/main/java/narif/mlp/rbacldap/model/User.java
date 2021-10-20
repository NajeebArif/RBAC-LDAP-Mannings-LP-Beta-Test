package narif.mlp.rbacldap.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USERS")
public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Integer age;
    private String phone;
    private Role role;
    private String password;
    private String managerName;
    private String address;
    private String tag;
}
