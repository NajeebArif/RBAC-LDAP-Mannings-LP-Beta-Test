package narif.mlp.rbacldap.repositories;

import narif.mlp.rbacldap.model.LdapUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DataLdapTest
@DisplayName("LDAP User Repository specs:")
class LdapUserRepositoryTest {

    public static final String JOHN = "John";
    public static final String DOE = "Doe";
    public static final String EMAIL_COM = "@email.com";
    @Autowired
    private LdapUserRepository ldapUserRepository;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    @Test
    @DisplayName("Should save a user in the ldap repository.")
    void testSaveUser(){
        LdapUser ldapUser = createLdapUser();
        System.out.println(ldapUser);
        final var savedUser = ldapUserRepository.save(ldapUser);
        assertThat(savedUser).isNotNull().isEqualTo(ldapUser);
    }

    @Test
    @DisplayName("Should return the user for a given email id")
    void testEmailId(){
        final var newUser = createLdapUser();
        ldapUserRepository.save(newUser);
        System.out.println("User Saved!!");
        System.out.println("Searching the ldap server for a given email id.");
        final var byEmailId = ldapUserRepository.findByEmailId(newUser.getEmailId());
        System.out.println("Found User: "+byEmailId);
        assertThat(byEmailId).isNotEmpty().get().matches(ldapUser -> ldapUser.getFirstName().equals("John"));
    }

    private LdapUser createLdapUser() {
        final var emailId = JOHN+ getRandomText() + EMAIL_COM;
        LdapUser ldapUser = new LdapUser(emailId);
        ldapUser.setFirstName(JOHN);
        ldapUser.setLastName(DOE);
        ldapUser.setPassword("someRawPassword");
        return ldapUser;
    }

    private String getRandomText() {
        return String.valueOf(System.currentTimeMillis());
    }

    @Test
    @DisplayName("Should return all the ldap users.")
    void fetchAllUsers(){
        final var all = ldapUserRepository.findAll();
        assertThat(all).isNotEmpty();
        all.forEach(System.out::println);
    }
}