package calendar.service;

import calendar.domain.Account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import calendar.domain.Event;
import calendar.repository.EventRepository;
import java.util.Date;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private AuthenticationService authService;

    @Deprecated
    public List<Event> list(Account account, Date start, Date end) {
        /*
        List<Event> events = eventRepository.findByOwnerAndDateBetweenXAndY(account, start, end);
        events.addAll(eventRepository.findByParticipationAndDateBetweenXAndY(account, start, end));*/
        List<Event> events = eventRepository.findAll();
        return events;
    }
    
    public void save(Event event) {
        event.setOwner(authService.getUserLoggedIn());
        eventRepository.save(event);
    }
}
