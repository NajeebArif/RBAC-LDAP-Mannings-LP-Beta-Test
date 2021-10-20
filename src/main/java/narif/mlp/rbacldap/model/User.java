package narif.mlp.rbacldap.model;

import lombok.Data;

@Data
public class User {

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
