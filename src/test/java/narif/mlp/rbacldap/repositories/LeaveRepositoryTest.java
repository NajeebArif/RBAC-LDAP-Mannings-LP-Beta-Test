package narif.mlp.rbacldap.repositories;

import narif.mlp.rbacldap.model.Leave;
import narif.mlp.rbacldap.model.enums.LeaveType;
import narif.mlp.rbacldap.model.enums.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Leave Repository Specs:")
class LeaveRepositoryTest {

    @Autowired
    private LeaveRepository leaveRepository;

    @Test
    @DisplayName("should save a leave entry in the database")
    void testSave(){
        final Leave leave = createLeave();

        final var save = leaveRepository.save(leave);
        assertThat(save).isNotNull().matches(s->s.getType().equals(LeaveType.PAID_LEAVE));
    }

    private Leave createLeave() {
        final var leave = new Leave();
        leave.setDays(2);
        leave.setComment("Casual Leave: Going out");
        leave.setEndDate(LocalDate.now().plusDays(2));
        leave.setStartDate(LocalDate.now());
        leave.setReason("Needed a break");
        leave.setStatus(Status.REQUESTED);
        leave.setType(LeaveType.PAID_LEAVE);
        return leave;
    }
}