package narif.mlp.rbacldap.repositories;

import narif.mlp.rbacldap.model.LdapUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataLdapTest
@DisplayName("LDAP User Repository specs:")
class LdapUserRepositoryTest {

    @Autowired
    private LdapUserRepository ldapUserRepository;

    @Test
    @DisplayName("Should save a user in the ldap repository.")
    void testSaveUser(){
        LdapUser ldapUser = createLdapUser();
        System.out.println(ldapUser);
        final var savedUser = ldapUserRepository.save(ldapUser);
        assertThat(savedUser).isNotNull().isEqualTo(ldapUser);
    }

    private LdapUser createLdapUser() {
        LdapUser ldapUser = new LdapUser("john@email.com");
        ldapUser.setFirstName("John");
        ldapUser.setLastName("Doe");
        ldapUser.setPassword("someRawPassword");
        return ldapUser;
    }

    @Test
    @DisplayName("Should return all the ldap users.")
    void fetchAllUsers(){
        final var all = ldapUserRepository.findAll();
        assertThat(all).isNotEmpty();
        all.forEach(System.out::println);
    }
}