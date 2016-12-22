package calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import calendar.domain.Event;
import calendar.repository.AccountRepository;
import calendar.repository.EventRepository;
import calendar.service.EventService;

@Controller

public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eRepo;
    @Autowired
    private AccountRepository accountRepo;

    @RequestMapping(value = "/newevent", method = RequestMethod.GET)
    public String newEvent(Model model) {
        return "newevent";
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String add(Event event) {
        eventService.save(event);
        return "redirect:/calendar";
    }
    
}
