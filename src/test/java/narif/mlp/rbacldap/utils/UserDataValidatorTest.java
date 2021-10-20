package narif.mlp.rbacldap.utils;

import narif.mlp.rbacldap.model.Role;
import narif.mlp.rbacldap.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.*;
import java.util.Set;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("User Data Validator Specs:")
@SpringBootTest
public class UserDataValidatorTest {

    final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator;
    User user;
    Set<ConstraintViolation<User>> constraintViolations;

    @BeforeEach
    public void init(){
        validator = validatorFactory.getValidator();
        user = new User();
    }

    @Test
    @DisplayName("Should throw constraint violation exception for first name, if it is not provided in the user model.")
    void testValidFirstName(){
        constraintViolations = validator.validate(user);
        final var firstName = constraintViolations.stream().filter(constraintViolationsFor("firstName")).findFirst();
        assertThat(firstName).isNotEmpty();
    }

    @Test
    @DisplayName("Should throw constraint violation exception for last name, if it is not provided in the user model.")
    void testValidLastName(){
        constraintViolations = validator.validate(user);
        final var propertyName = "lastName";
        final var lastName = constraintViolations.stream()
                .filter(constraintViolationsFor(propertyName)).findFirst();
        assertThat(lastName).isNotEmpty();
    }

    @Test
    @DisplayName("Should throw constraint violation exception for email, if it is not provided in the user model.")
    void testValidEmailId(){
        constraintViolations = validator.validate(user);
        final var propertyName = "emailId";
        final var email = constraintViolations.stream()
                .filter(constraintViolationsFor(propertyName).and(hasErrorMessage("User should provide a value for email."))).findFirst();
        assertThat(email).isNotEmpty();
    }

    @Test
    @DisplayName("Should throw constraint violation exception for email, if it is not valid in the user model.")
    void testValidEmailId2(){
        user.setEmailId("invalidEmailId");
        constraintViolations = validator.validate(user);
        final var propertyName = "emailId";
        final var email = constraintViolations.stream()
                .filter(constraintViolationsFor(propertyName).and(hasErrorMessage("User should provide a valid email address."))).findFirst();
        assertThat(email).isNotEmpty();
    }

    @Test
    @DisplayName("Should throw constraint violation exception for age, if it is not provided in the user model.")
    void testValidAge(){
        constraintViolations = validator.validate(user);
        final var propertyName = "age";
        final var lastName = constraintViolations.stream()
                .filter(constraintViolationsFor(propertyName)).findFirst();
        assertThat(lastName).isNotEmpty();
    }

    @Test
    @DisplayName("Should throw constraint violation exception for role, if it is not provided in the user model.")
    void testValidRole(){
        constraintViolations = validator.validate(user);
        final var propertyName = "role";
        final var lastName = constraintViolations.stream()
                .filter(constraintViolationsFor(propertyName)).findFirst();
        assertThat(lastName).isNotEmpty();
    }

    @Test
    @DisplayName("Should throw constraint violation exception for address, if it is not provided in the user model.")
    void testValidAddress(){
        constraintViolations = validator.validate(user);
        final var propertyName = "address";
        final var lastName = constraintViolations.stream()
                .filter(constraintViolationsFor(propertyName)).findFirst();
        assertThat(lastName).isNotEmpty();
    }

    @Nested
    @DisplayName("Password validation specs:")
    class PasswordValidator{
        @Test
        @DisplayName("Should throw constraint violation exception for password, if it is not provided in the user model.")
        void testNullPassword(){
            constraintViolations = validator.validate(user);
            final var propertyName = "password";
            final var lastName = constraintViolations.stream()
                    .filter(constraintViolationsFor(propertyName)).findFirst();
            assertThat(lastName).isNotEmpty();
        }

        @Test
        @DisplayName("Should throw constraint violation exception for password, if it is empty.")
        void testEmptyPassword(){
            user.setPassword("       ");
            constraintViolations = validator.validate(user);
            final var propertyName = "password";
            final var lastName = constraintViolations.stream()
                    .filter(constraintViolationsFor(propertyName)).findFirst();
            assertThat(lastName).isNotEmpty();
        }

        @Test
        @DisplayName("Should throw constraint violation exception for password, if it is less than 8 chars.")
        void testTooShortPassword(){
            user.setPassword("aB3de$");
            constraintViolations = validator.validate(user);
            final var propertyName = "password";
            final var lastName = constraintViolations.stream()
                    .filter(constraintViolationsFor(propertyName)).findFirst();
            assertThat(lastName).isNotEmpty();
        }

        @Test
        @DisplayName("Should throw constraint violation exception for password, if it is greater than 16 chars.")
        void testTooLongPassword(){
            user.setPassword("aB3de$78901234567890");
            constraintViolations = validator.validate(user);
            final var propertyName = "password";
            final var lastName = constraintViolations.stream()
                    .filter(constraintViolationsFor(propertyName)).findFirst();
            assertThat(lastName).isNotEmpty();
        }

        @Test
        @DisplayName("Should throw constraint violation exception for password, if it does not contain uppercase char.")
        void testMissingUppercaseCharPassword(){
            user.setPassword("ab3de$7890");
            constraintViolations = validator.validate(user);
            final var propertyName = "password";
            final var lastName = constraintViolations.stream()
                    .filter(constraintViolationsFor(propertyName)).findFirst();
            assertThat(lastName).isNotEmpty();
        }

        @Test
        @DisplayName("Should throw constraint violation exception for password, if it does not contain lowercase char.")
        void testMissingLowercaseCharPassword(){
            user.setPassword("AB3DE$7890");
            constraintViolations = validator.validate(user);
            final var propertyName = "password";
            final var lastName = constraintViolations.stream()
                    .filter(constraintViolationsFor(propertyName)).findFirst();
            assertThat(lastName).isNotEmpty();
        }

        @Test
        @DisplayName("Should throw constraint violation exception for password, if it does not contain digits.")
        void testMissingDigitsPassword(){
            user.setPassword("abDde$AbcD");
            constraintViolations = validator.validate(user);
            final var propertyName = "password";
            final var lastName = constraintViolations.stream()
                    .filter(constraintViolationsFor(propertyName)).findFirst();
            assertThat(lastName).isNotEmpty();
        }

        @Test
        @DisplayName("Should throw constraint violation exception for password, if it does not contain special char.")
        void testMissingSpecialCharPassword(){
            user.setPassword("ab3deF7890");
            constraintViolations = validator.validate(user);
            final var propertyName = "password";
            final var lastName = constraintViolations.stream()
                    .filter(constraintViolationsFor(propertyName)).findFirst();
            assertThat(lastName).isNotEmpty();
        }
    }

    @Test
    @DisplayName("Should PASS all the constraint checks if all valid attributes are provided for user")
    void testValidUser(){
        createValidUser();
        final var constraintViolations = validator.validate(user);
        assertThat(constraintViolations).isEmpty();
    }

    private void createValidUser() {
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAddress("123 Street, Alpha Beta.");
        user.setPhone("123-456-6789");
        user.setAge(24);
        user.setEmailId("John.Doe@email.com");
        user.setPassword("Ak@1jugpe3");
        user.setRole(Role.ADMIN);
    }

    private Predicate<ConstraintViolation<User>> hasErrorMessage(String expectedMessage) {
        return userConstraintViolation -> userConstraintViolation.getMessage().equals(expectedMessage);
    }

    private Predicate<ConstraintViolation<User>> constraintViolationsFor(String propertyName) {
        return userConstraintViolation -> userConstraintViolation.getPropertyPath().toString().equals(propertyName);
    }
}
