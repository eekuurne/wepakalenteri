package calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import calendar.domain.Event;
import calendar.repository.EventRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepo;

    @RequestMapping(value = "/create")
    public String newEvent() {
        return "newevent";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(Event event) {
        eventRepo.save(event);
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Event e = eventRepo.getOne(id);
        model.addAttribute("event", e);
        
        return "edit_event";
    }
    
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String handleEdit(Event e) {
        eventRepo.save(e);
        
        return "redirect:/";
    }

}
