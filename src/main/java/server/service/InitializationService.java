package server.service;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.domain.Account;
import server.domain.Event;
import server.repository.AccountRepository;
import server.repository.EventRepository;

@Service
public class InitializationService {

    private final Long dayInMillis;
    
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EventRepository eventRepo;

    public InitializationService() {
        this.dayInMillis = new Long (1000 * 60 * 60 * 24);
    }

    @PostConstruct
    public void initialize() {
        //Users
        Account a = new Account();
        a.setUsername("User1");
        a.setPassword(encoder.encode("Password"));
        a.setRole("USER");
        accountRepo.save(a);
        
        //Events
        Event e = new Event();
        e.setOwner(a);
        e.setTitle("Event1");
        e.setDescription("Description1");
        e.setPlace("Somewhere");
        e.setStartTime(new Date(System.currentTimeMillis()));
        e.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 3));
        eventRepo.save(e);
        
        e.setOwner(a);
        e.setTitle("Event2");
        e.setDescription("Description2");
        e.setPlace("Elsewhere");
        e.setStartTime(new Date(System.currentTimeMillis() + dayInMillis * 7));
        e.setEndTime(new Date(System.currentTimeMillis() + dayInMillis * 10));
        eventRepo.save(e);

    }
}
