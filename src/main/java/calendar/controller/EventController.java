package calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import calendar.domain.Event;
import calendar.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/create")
    public String newEvent() {
        return "newevent";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(Event event) {
        eventService.save(event);
        return "redirect:/calendar";
    }

}
