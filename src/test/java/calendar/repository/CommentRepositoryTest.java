package calendar.repository;

import calendar.domain.Account;
import calendar.domain.Comment;
import calendar.domain.Event;
import calendar.repository.AccountRepository;
import calendar.repository.CommentRepository;
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
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private PasswordEncoder encoder;

    public CommentRepositoryTest() {
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
    public void testfindByEventOrderByPostedAsc() {
        
        Account a = new Account();
        a.setUsername("user2");
        String password= encoder.encode("Password2");
        a.setPassword(password);
        a.setRole("USER");
        accountRepository.save(a);  

      
        Event e = new Event();
        e.setOwner(a);
        e.setTitle("Event1");
        e.setDescription("Description1");
        e.setPlace("Somewhere1");
        e.setStartTime(new Date(System.currentTimeMillis()));
        e.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 3));
        eventRepository.save(e);

        Comment c1 = new Comment();
        c1.setEvent(e);
        c1.setPoster(a);
        c1.setMessage("Hello");
        c1.setPosted(new Date(System.currentTimeMillis()));
        commentRepository.save(c1);

        Comment c2 = new Comment();
        c2.setEvent(e);
        c2.setPoster(a);
        c2.setMessage("Hi");
        c2.setPosted(new Date(System.currentTimeMillis()));
        commentRepository.save(c2);

        List<Comment> retrieved = commentRepository.findByEventOrderByPostedAsc(e);
        assertNotNull(retrieved);
        assertEquals(2, retrieved.size());
        assertEquals("Hello", retrieved.get(0).getMessage());
        assertEquals("Hi", retrieved.get(1).getMessage());
    }
}
