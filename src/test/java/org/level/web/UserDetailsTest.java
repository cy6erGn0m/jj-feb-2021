package org.level.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.levelp.model.UsersRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DetailsService.class})
public class UserDetailsTest {
    @MockBean
    private UsersRepository users;

    @MockBean
    private PasswordEncoder encoder;

    @Autowired
    private DetailsService service;

    @Test
    public void smokeTest() {
    }

    @Test
    public void testAdmin() {
        Mockito.when(encoder.encode("admin")).thenReturn("encryptedAdmin");

        UserDetails expectedUser = User.withUsername("admin")
                .password("encryptedAdmin")
                .roles("ADMIN", "USER")
                .build();

        UserDetails result = service.loadUserByUsername("admin");
        assertNotNull(result);
        assertEquals(expectedUser.getUsername(), result.getUsername());
        assertEquals(expectedUser.getAuthorities(), result.getAuthorities());
        assertEquals(expectedUser.getPassword(), result.getPassword());
    }

    @Test
    public void testUserFromRepo() {
        org.levelp.model.User user = new org.levelp.model.User(
                "user-1",
                "encryptedPassword",
                true
        );

        Mockito.when(users.findByLogin(user.getLogin())).thenReturn(user);

        UserDetails expectedUser = User.withUsername(user.getLogin())
                .password(user.getPassword())
                .roles("USER")
                .build();

        UserDetails result = service.loadUserByUsername(user.getLogin());
        assertNotNull(result);
        assertEquals(expectedUser.getUsername(), result.getUsername());
        assertEquals(expectedUser.getAuthorities(), result.getAuthorities());
        assertEquals(expectedUser.getPassword(), result.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNonExistingUser() {
        service.loadUserByUsername("user-0");
    }
}
