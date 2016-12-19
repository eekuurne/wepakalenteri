package calendar.service;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import calendar.domain.Account;
import calendar.domain.Comment;
import calendar.domain.Event;
import calendar.domain.Friendship;
import calendar.domain.Participation;
import calendar.repository.AccountRepository;
import calendar.repository.CommentRepository;
import calendar.repository.EventRepository;
import calendar.repository.FriendshipRepository;
import calendar.repository.ParticipationRepository;
import java.util.List;

/**
 * A service that adds test data to the database in use.
 * For now works in Heroku too.
 */
@Service
public class InitializationService {

    public static final Long dayInMillis = new Long (1000 * 60 * 60 * 24);
    
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private ParticipationRepository participationRepo;
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private FriendshipRepository friendRepo;

    /**
     * Adds test data to the database.
     */
    @PostConstruct
    public void initialize() {
        if (countEntries() > 0) {
            return;
        }
        
        //Users
        Account a1 = new Account();
        a1.setUsername("User1");
        a1.setPassword(encoder.encode("Password"));
        a1.setRole("USER");
        accountRepo.save(a1);
        
        Account a2 = new Account();
        a2.setUsername("User2");
        a2.setPassword(encoder.encode("Password"));
        a2.setRole("USER");
        accountRepo.save(a2);
        
        Account a3 = new Account();
        a3.setUsername("User3");
        a3.setPassword(encoder.encode("Password"));
        a3.setRole("USER");
        accountRepo.save(a3);
        
        //Events
        Event e1 = new Event();
        e1.setOwner(a1);
        e1.setTitle("Event1");
        e1.setDescription("Description1");
        e1.setPlace("Somewhere");
        e1.setStartTime(new Date(System.currentTimeMillis()));
        e1.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 3));
        eventRepo.save(e1);
        
        Event e2 = new Event();
        e2.setOwner(a2);
        e2.setTitle("Event2");
        e2.setDescription("Description2");
        e2.setPlace("Elsewhere");
        e2.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 7));
        e2.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 10));
        eventRepo.save(e2);
        
        Event e3 = new Event();
        e3.setOwner(a3);
        e3.setTitle("Event3");
        e3.setDescription("Description3");
        e3.setPlace("Elsewhere?");
        e3.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 1));
        e3.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 2));
        eventRepo.save(e3);
        
        //Participation
        Participation p1 = new Participation();
        p1.setAccount(a2);
        p1.setEvent(e1);
        p1.setAccepted(true);
        participationRepo.save(p1);
        
        Participation p2 = new Participation();
        p2.setAccount(a1);
        p2.setEvent(e2);
        p2.setAccepted(true);
        participationRepo.save(p2);
        
        Participation p3 = new Participation();
        p3.setAccount(a1);
        p3.setEvent(e3);
        p3.setAccepted(true);
        participationRepo.save(p3);

        //Comment
        Comment c1 = new Comment();
        c1.setEvent(e1);
        c1.setPoster(a1);
        c1.setMessage("Hello");
        c1.setPosted(new Date(System.currentTimeMillis() + dayInMillis));
        commentRepo.save(c1);
        
        Comment c2 = new Comment();
        c2.setEvent(e1);
        c2.setPoster(a2);
        c2.setMessage("This one should be on top if these are sorted correctly");
        c2.setPosted(new Date(System.currentTimeMillis()));
        commentRepo.save(c2);
        
        
        //Friendship
        Friendship f1 = new Friendship();
        f1.setRequester(a1);
        f1.setTarget(a2);
        f1.setAccepted(true);
        friendRepo.save(f1);
        
        Friendship f2 = new Friendship();
        f2.setRequester(a3);
        f2.setTarget(a2);
        f2.setAccepted(true);
        friendRepo.save(f2);
        
        Friendship f3 = new Friendship();
        f3.setRequester(a3);
        f3.setTarget(a1);
        f3.setAccepted(false);
        friendRepo.save(f3);
        
    }

    /**
     * Counts the number of entries in the DB.
     * 
     * @return The number of entries in the DB
     */
    private Long countEntries() {
        //Checks if the DB has data
        Long count = new Long(0);
        count += accountRepo.count();
        count += commentRepo.count();
        count += eventRepo.count();
        count += friendRepo.count();
        count += participationRepo.count();
        return count;
    }
}
