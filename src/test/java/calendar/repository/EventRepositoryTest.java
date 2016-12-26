package calendar.repository;

import calendar.domain.Account;
import calendar.domain.Event;
import calendar.domain.Participation;
import calendar.repository.AccountRepository;
import calendar.repository.EventRepository;
import calendar.repository.ParticipationRepository;
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

    @Autowired
    private ParticipationRepository participationrepository;

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
        //a1 is an owner in only event e1. Both e1 and e2 start between X and Y

        Account a1 = new Account();
        a1.setUsername("user3");
        String password = encoder.encode("Password3");
        a1.setPassword(password);
        a1.setRole("USER");
        accountRepository.save(a1);

        Account a2 = new Account();
        a2.setUsername("user4");
        String password2 = encoder.encode("Password4");
        a2.setPassword(password2);
        a2.setRole("USER");
        accountRepository.save(a2);

        Event e1 = new Event();
        e1.setOwner(a1);
        e1.setTitle("Event2");
        e1.setDescription("Description2");
        e1.setPlace("Somewhere2");
        e1.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 1));
        e1.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 2));
        eventRepository.save(e1);

        Event e2 = new Event();
        e2.setOwner(a2);
        e2.setTitle("Event3");
        e2.setDescription("Description3");
        e2.setPlace("Somewhere3");
        e2.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 4));
        e2.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 5));
        eventRepository.save(e2);

        List<Event> retrieved = eventRepository.findByOwnerAndDateBetweenXAndY(a1, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + dayInMillis * 5));
        assertNotNull(retrieved);
        assertEquals(1, retrieved.size());
        assertEquals("Event2", retrieved.get(0).getTitle());
        assertEquals("Description2", retrieved.get(0).getDescription());
        assertEquals("Somewhere2", retrieved.get(0).getPlace());
        assertEquals(a1.getUsername(), retrieved.get(0).getOwner().getUsername());

        //a1 is an owner in both events e1 and e3 but only e1 starts between X and Y 
        
        Event e3= new Event();
        e3.setOwner(a1);
        e3.setTitle("Event4");
        e3.setDescription("Description4");
        e3.setPlace("Somewhere4");
        e3.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 4));
        e3.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 5));
        eventRepository.save(e3);
        
        List<Event> retrieved2 = eventRepository.findByOwnerAndDateBetweenXAndY(a1, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + dayInMillis * 3));
        assertNotNull(retrieved2);
        assertEquals(1, retrieved2.size());
        assertEquals("Event2", retrieved2.get(0).getTitle());
        assertEquals("Description2", retrieved2.get(0).getDescription());
        assertEquals("Somewhere2", retrieved2.get(0).getPlace());
        assertEquals(a1.getUsername(), retrieved2.get(0).getOwner().getUsername());
        
    }

    @Test
    public void testFindByParticipationAndDateBetweenXAndY() {
        //a1 is a participant in only event e1. Both e1 and e2 start between X and Y

        Account a1 = new Account();
        a1.setUsername("user5");
        String password = encoder.encode("Password5");
        a1.setPassword(password);
        a1.setRole("USER");
        accountRepository.save(a1);

        Account a2 = new Account();
        a2.setUsername("user6");
        String password2 = encoder.encode("Password6");
        a2.setPassword(password2);
        a2.setRole("USER");
        accountRepository.save(a2);

        Event e1 = new Event();
        e1.setOwner(a1);
        e1.setTitle("Event5");
        e1.setDescription("Description5");
        e1.setPlace("Somewhere5");
        e1.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 1));
        e1.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 2));
        eventRepository.save(e1);

        Event e2 = new Event();
        e2.setOwner(a1);
        e2.setTitle("Event6");
        e2.setDescription("Description6");
        e2.setPlace("Somewhere6");
        e2.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 4));
        e2.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 5));
        eventRepository.save(e2);

        Participation p1 = new Participation();
        p1.setAccount(a2);
        p1.setEvent(e1);
        p1.setAccepted(true);
        participationrepository.save(p1);

        List<Event> retrieved = eventRepository.findByParticipationAndDateBetweenXAndY(a2, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + dayInMillis * 5));
        assertNotNull(retrieved);
        assertEquals(1, retrieved.size());
        assertEquals("Event5", retrieved.get(0).getTitle());
        assertEquals("Description5", retrieved.get(0).getDescription());
        assertEquals("Somewhere5", retrieved.get(0).getPlace());
        assertEquals(a1.getUsername(), retrieved.get(0).getOwner().getUsername());

        //a1 is a participant in both events e1 and e2 but only e1 starts between X and Y 
        Participation p2 = new Participation();
        p2.setAccount(a2);
        p2.setEvent(e2);
        p2.setAccepted(true);
        participationrepository.save(p2);

        List<Event> retrieved2 = eventRepository.findByParticipationAndDateBetweenXAndY(a2, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + dayInMillis * 3));
        assertNotNull(retrieved2);
        assertEquals(1, retrieved2.size());
        assertEquals("Event5", retrieved2.get(0).getTitle());
        assertEquals("Description5", retrieved2.get(0).getDescription());
        assertEquals("Somewhere5", retrieved2.get(0).getPlace());
        assertEquals(a1.getUsername(), retrieved2.get(0).getOwner().getUsername());
    }

}
