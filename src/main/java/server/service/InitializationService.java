package server.service;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.domain.Account;
import server.domain.Event;
import server.domain.Participation;
import server.repository.AccountRepository;
import server.repository.EventRepository;
import server.repository.ParticipationRepository;

@Service
public class InitializationService {

    private final Long dayInMillis;
    
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private ParticipationRepository participationRepo;

    public InitializationService() {
        this.dayInMillis = new Long (1000 * 60 * 60 * 24);
    }

    @PostConstruct
    public void initialize() {
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
        e2.setOwner(a1);
        e2.setTitle("Event2");
        e2.setDescription("Description2");
        e2.setPlace("Elsewhere");
        e2.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 7));
        e2.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 10));
        eventRepo.save(e2);
        
        //Participation
        Participation p1 = new Participation();
        p1.setAccount(a2);
        p1.setEvent(e1);
        p1.setAccepted(true);
        participationRepo.save(p1);
        
        Participation p2 = new Participation();
        p2.setAccount(a1);
        p2.setEvent(e1);
        p2.setAccepted(true);
        participationRepo.save(p2);

    }
}
