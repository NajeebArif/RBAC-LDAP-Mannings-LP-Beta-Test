package narif.mlp.rbacldap.model;

import lombok.Data;
import narif.mlp.rbacldap.model.enums.LeaveType;
import narif.mlp.rbacldap.model.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private LeaveType type;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer days;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String comment;

    private User user;
}
