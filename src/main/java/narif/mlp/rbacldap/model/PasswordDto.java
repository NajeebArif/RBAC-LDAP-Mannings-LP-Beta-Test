package narif.mlp.rbacldap.model;

import lombok.Data;

@Data
public class PasswordDto {

    private String oldPassword;
    private String newPassword;
}
