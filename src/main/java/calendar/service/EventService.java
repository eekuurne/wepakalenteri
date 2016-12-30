package calendar.service;

import calendar.domain.Account;
import org.springframework.stereotype.Service;
import calendar.domain.Event;
import calendar.domain.Participation;
import calendar.repository.EventRepository;
import calendar.repository.ParticipationRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventService {
    
    @Autowired
    private ParticipationRepository partRepo;
    
    @Autowired
    private EventRepository eventRepo;
    
    @Autowired
    private AuthenticationService authService;
    
    public boolean isParticipatingIn(Account a, Event e) {
        Participation p = partRepo.findByEventAndAccount(e, a);
        Account owner = e.getOwner();
        
        if (owner.equals(a) || p != null) {
            return true;
        }
        
        return false;
    }
    
    public Event saveEvent(Event event, String title, String place, String description, Date startDate, Date startTime, Date endDate, Date endTime) {
        event.setTitle(title);
        event.setPlace(place);
        event.setDescription(description);
        event.setOwner(authService.getUserLoggedIn());
        
        startTime = createEventTime(startDate, startTime);
        event.setStartTime(startTime);
        
        endTime = createEventTime(endDate, endTime);
        if (endTime.before(startTime)) {
            endTime = startTime;
        }
        event.setEndTime(endTime);
        
        eventRepo.save(event);
        return event;
    }

    private Date createEventTime(Date date, Date time) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        if (time == null) {
            time = new Date(System.currentTimeMillis());
        }
        Date eventTime = dateTime(date, time);
        return eventTime;
    }
    
    private Date dateTime(Date date, Date time) {
        return new Date(
                     date.getYear(), date.getMonth(), date.getDate(), 
                     time.getHours(), time.getMinutes()
        );
    }
    
    public String timeFromDateTime(Date date) {
        int hours = date.getHours();
        int minutes = date.getMinutes();
        String time = "";
        
        if (hours < 10) {
            time += "0";
        }
        time += hours + ":";
        
        if (minutes < 10) {
            time += "0";
        }
        time += minutes;

        return time;
    }
}
