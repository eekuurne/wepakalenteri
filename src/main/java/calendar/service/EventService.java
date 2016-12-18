package calendar.service;

import calendar.domain.Account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import calendar.domain.Event;
import calendar.repository.AccountRepository;
import calendar.repository.EventRepository;
import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    public List<Event> list(Account account, Date start, Date end) {
        /*
        List<Event> events = eventRepository.findByOwnerAndDateBetweenXAndY(account, start, end);
        events.addAll(eventRepository.findByParticipationAndDateBetweenXAndY(account, start, end));*/
        List<Event> events = eventRepository.findAll();
        return events;
    }
    
    public void save(Event event) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account a = accountRepository.findByUsername(auth.getName());
        event.setOwner(a);
        eventRepository.save(event);
    }
}
