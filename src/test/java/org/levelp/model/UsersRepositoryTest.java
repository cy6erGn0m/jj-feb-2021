package org.levelp.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelp.TestConfiguration;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    private Date now = new Date();

    @Before
    public void configure() {
        User newUser = new User("login777", "pass", false);
        newUser.setBirthDate(now);
        usersRepository.save(newUser);
    }

    @Test
    public void findByLogin() {
        assertNull(usersRepository.findByLogin("non existing user"));

        User found = usersRepository.findByLogin("login777");
        assertNotNull(found);
        assertEquals("login777", found.getLogin());
    }

    @Test
    public void findByLoginAndPassword() {
        assertNull(usersRepository.findByLoginAndPassword("some user", "ppp"));
        assertNull(usersRepository.findByLoginAndPassword("login777", "pass1"));

        User found = usersRepository.findByLoginAndPassword("login777", "pass");
        assertNotNull(found);
        assertEquals("login777", found.getLogin());
        assertEquals("pass", found.getPassword());
    }

    @Test
    public void findByBirthDate() {
        List<User> foundByNow = usersRepository.findByBirthDateIsLessThanEqual(now);
        assertEquals(1, foundByNow.size());
        assertEquals("login777", foundByNow.get(0).getLogin());

        Date dateBefore = new Date(now.getTime() - 100000000);
        List<User> foundBefore = usersRepository.findByBirthDateIsLessThanEqual(dateBefore);
        assertEquals(0, foundBefore.size());
    }

    @Test
    public void findByIsAdmin() {
        assertEquals("login777", usersRepository.findByIsAdmin(false).get(0).getLogin());
        assertTrue(usersRepository.findByIsAdmin(true).isEmpty());
    }
}
