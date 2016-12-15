package server.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.domain.Event;
import server.repository.EventRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    public List<Event> list() {
        return eventRepository.findAll();
    }
    
    public void save(Event event) {
        eventRepository.save(event);
    }
}
