package narif.mlp.rbacldap.services;

import narif.mlp.rbacldap.exceptions.UserAlreadyRegisteredException;
import narif.mlp.rbacldap.model.LdapUser;
import narif.mlp.rbacldap.model.enums.Role;
import narif.mlp.rbacldap.model.User;
import narif.mlp.rbacldap.repositories.LdapUserRepository;
import narif.mlp.rbacldap.repositories.UserJpaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("User Service Specs:")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UsersServiceTest {

    public static final String EMAIL_NOT_FOUNT = "email@email.com";
    public static final String JOHN_EMAIL_COM = "john@email.com";

    @Mock
    UserJpaRepo userJpaRepoMock;
    @Mock
    LdapUserRepository ldapUserRepositoryMock;

    UsersService usersService;

    @BeforeEach
    public void init() {
        usersService = new UsersService(userJpaRepoMock, ldapUserRepositoryMock);
        when(userJpaRepoMock.findByEmailId(EMAIL_NOT_FOUNT)).thenReturn(Optional.empty());
        when(userJpaRepoMock.findByEmailId(JOHN_EMAIL_COM)).thenReturn(Optional.of(new User()));
    }

    @Test
    @DisplayName("should return false if a given email is not present in the db.")
    void testEmailNotPresent() {
        final var userRegistered = usersService.isUserRegistered(EMAIL_NOT_FOUNT);
        assertThat(userRegistered).isNotNull().isFalse();
    }

    @Test
    @DisplayName("Should return true if user is registered.")
    void testIsRegistered() {
        assertThat(usersService.isUserRegistered(JOHN_EMAIL_COM)).isTrue();
    }

    @Test
    @DisplayName("Should not throw exception while creating user if the user is not registered")
    void testCreateUser1() {
        final var user = createValidUser();
        when(userJpaRepoMock.save(user)).thenReturn(user);

        assertThatCode(()->usersService.createUser(user)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Should throw an exception while creating the user if the user is registered.")
    void testCreateUser2() {
        final var user = new User();
        user.setEmailId(JOHN_EMAIL_COM);
        assertThatCode(()->usersService.createUser(user)).isInstanceOf(UserAlreadyRegisteredException.class)
                .hasMessage(UserAlreadyRegisteredException.genericMessage);
    }

    @Test
    @DisplayName("Should return the saved entity after saving via repository")
    void testRepoSave(){
        final var validUser = createValidUser();
        when(userJpaRepoMock.save(validUser)).thenReturn(validUser);
        final var user = usersService.createUser(validUser);
        assertThat(user).isNotNull().matches(user1 -> user1.getEmailId().equals(validUser.getEmailId()));
        verify(userJpaRepoMock).save(validUser);
    }

    @Test
    @DisplayName("Should call ldap repository save after saving to db.")
    void testSaveToLdap(){
        final var validUser = createValidUser();
        when(userJpaRepoMock.save(validUser)).thenReturn(validUser);
        final var ldapUser = validUser.getLdapUser();
        when(ldapUserRepositoryMock.save(any(LdapUser.class))).thenReturn(ldapUser);
        final var user = usersService.createUser(validUser);
        assertThat(user).isNotNull().matches(user1 -> user1.getEmailId().equals(validUser.getEmailId()));
        verify(userJpaRepoMock).save(validUser);
        verify(ldapUserRepositoryMock).save(any(LdapUser.class));
    }

    private User createValidUser() {
        final var user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAddress("123 Street, Alpha Beta.");
        user.setPhone("123-456-6789");
        user.setAge(24);
        user.setEmailId("John.Doe@email.com");
        user.setPassword("Ak@1jugpe3");
        user.setRole(Role.ADMIN);
        return user;
    }

}