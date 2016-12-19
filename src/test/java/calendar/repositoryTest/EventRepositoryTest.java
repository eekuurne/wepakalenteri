package calendar.repositoryTest;

import calendar.domain.Account;
import calendar.domain.Event;
import calendar.repository.AccountRepository;
import calendar.repository.EventRepository;
import static calendar.service.InitializationService.dayInMillis;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AccountRepository accountRepository;

    public EventRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testfindByOwnerAndDateBetweenXAndY() {

        Account a = new Account();
        a.setUsername("user2");
        String password = encoder.encode("Password2");
        a.setPassword(password);
        a.setRole("USER");
        accountRepository.save(a);

        Event e = new Event();
        e.setOwner(a);
        e.setTitle("Event2");
        e.setDescription("Description2");
        e.setPlace("Somewhere2");
        e.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 1));
        e.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 2));
        eventRepository.save(e);

        Event e2 = new Event();
        e2.setOwner(a);
        e2.setTitle("Event3");
        e2.setDescription("Description3");
        e2.setPlace("Somewhere3");
        e2.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 4));
        e2.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 5));
        eventRepository.save(e);

        List<Event> retrieved = eventRepository.findByOwnerAndDateBetweenXAndY(a, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + dayInMillis * 3));
        assertNotNull(retrieved);
        assertEquals(1, retrieved.size());
        assertEquals("Event2", retrieved.get(0).getTitle());
        assertEquals("Description2", retrieved.get(0).getDescription());
        assertEquals("Somewhere2", retrieved.get(0).getPlace());
        assertEquals(a.getUsername(), retrieved.get(0).getOwner().getUsername());

    }

}
