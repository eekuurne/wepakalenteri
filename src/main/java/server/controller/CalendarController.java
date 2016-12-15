package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import server.domain.Event;
import server.service.EventService;

@Controller
public class CalendarController {
    
    @Autowired
    private EventService eventService;
    
    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("events", eventService.list());
        return "calendar";
    }
}
