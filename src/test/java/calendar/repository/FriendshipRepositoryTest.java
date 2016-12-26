package calendar.repository;

import calendar.domain.Account;
import calendar.domain.Event;
import calendar.domain.Friendship;
import calendar.repository.AccountRepository;
import calendar.repository.EventRepository;
import calendar.repository.FriendshipRepository;
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
public class FriendshipRepositoryTest {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AccountRepository accountRepository;

    public FriendshipRepositoryTest() {
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
    public void testFindFriendsByRequester() {

        Account a1 = new Account();
        a1.setUsername("user7");
        String password = encoder.encode("Password7");
        a1.setPassword(password);
        a1.setRole("USER");
        accountRepository.save(a1);

        Account a2 = new Account();
        a2.setUsername("user8");
        String password2 = encoder.encode("Password8");
        a2.setPassword(password2);
        a2.setRole("USER");
        accountRepository.save(a2);

        Friendship f1 = new Friendship();
        f1.setRequester(a1);
        f1.setTarget(a2);
        f1.setAccepted(true);
        friendshipRepository.save(f1);

        List<Account> retrieved = friendshipRepository.findFriendsByRequester(a1);
        assertNotNull(retrieved);
        assertEquals(1, retrieved.size());
        assertEquals("user8", retrieved.get(0).getUsername());
        assertEquals(password2, retrieved.get(0).getPassword());
        assertEquals("USER", retrieved.get(0).getRole());

    }

    @Test
    public void testFindFriendsByTarget() {

        Account a1 = new Account();
        a1.setUsername("user9");
        String password = encoder.encode("Password9");
        a1.setPassword(password);
        a1.setRole("USER");
        accountRepository.save(a1);

        Account a2 = new Account();
        a2.setUsername("user10");
        String password2 = encoder.encode("Password10");
        a2.setPassword(password2);
        a2.setRole("USER");
        accountRepository.save(a2);

        Account a3 = new Account();
        a3.setUsername("user11");
        String password3 = encoder.encode("Password11");
        a3.setPassword(password3);
        a3.setRole("USER");
        accountRepository.save(a3);

        Friendship f1 = new Friendship();
        f1.setRequester(a1);
        f1.setTarget(a2);
        f1.setAccepted(true);
        friendshipRepository.save(f1);

        Friendship f2 = new Friendship();
        f2.setRequester(a3);
        f2.setTarget(a2);
        f2.setAccepted(true);
        friendshipRepository.save(f2);

        List<Account> retrieved = friendshipRepository.findFriendsByTarget(a2);
        assertNotNull(retrieved);
        assertEquals(2, retrieved.size());
        assertEquals("user9", retrieved.get(0).getUsername());
        assertEquals(password, retrieved.get(0).getPassword());
        assertEquals("USER", retrieved.get(0).getRole());
        assertEquals("user11", retrieved.get(1).getUsername());
        assertEquals(password3, retrieved.get(1).getPassword());
        assertEquals("USER", retrieved.get(1).getRole());

    }
    
     @Test
    public void testFindFriendRequestsByTarget() {

        Account a1 = new Account();
        a1.setUsername("user12");
        String password = encoder.encode("Password12");
        a1.setPassword(password);
        a1.setRole("USER");
        accountRepository.save(a1);

        Account a2 = new Account();
        a2.setUsername("user13");
        String password2 = encoder.encode("Password13");
        a2.setPassword(password2);
        a2.setRole("USER");
        accountRepository.save(a2);

        Account a3 = new Account();
        a3.setUsername("user14");
        String password3 = encoder.encode("Password14");
        a3.setPassword(password3);
        a3.setRole("USER");
        accountRepository.save(a3);

        Friendship f1 = new Friendship();
        f1.setRequester(a1);
        f1.setTarget(a2);
        f1.setAccepted(false);
        friendshipRepository.save(f1);

        Friendship f2 = new Friendship();
        f2.setRequester(a3);
        f2.setTarget(a2);
        f2.setAccepted(true);
        friendshipRepository.save(f2);

        List<Account> retrieved = friendshipRepository.findRequestersByTarget(a2);
        assertNotNull(retrieved);
        assertEquals(1, retrieved.size());
        assertEquals("user12", retrieved.get(0).getUsername());
        assertEquals(password, retrieved.get(0).getPassword());
        assertEquals("USER", retrieved.get(0).getRole());
       

    }
}
