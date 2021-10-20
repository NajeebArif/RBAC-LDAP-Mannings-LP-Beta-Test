package narif.mlp.rbacldap.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Integer age;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    private String managerName;
    private String address;
    private String tag;
}
