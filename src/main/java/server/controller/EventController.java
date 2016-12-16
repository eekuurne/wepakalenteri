package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import server.domain.Event;
import server.service.EventService;

@Controller
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    @RequestMapping(value = "/newevent", method = RequestMethod.GET)
    public String newEvent(Model model) {
        model.addAttribute("events", eventService.list());
        return "newevent";
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String add(Event event) {
        eventService.save(event);
        return "redirect:/calendar";
    }
}
