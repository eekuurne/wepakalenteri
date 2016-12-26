package calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import calendar.domain.Event;
import calendar.repository.EventRepository;
import calendar.service.AuthenticationService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * A controller for handling events.
 */
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = "/create")
    public String create() {
        return "newevent";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(Event event) {
        event.setOwner(authService.getUserLoggedIn());
        eventRepo.save(event);

        return "redirect:/";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Event e = eventRepo.getOne(id);

        if (!authService.getUserLoggedIn().equals(e.getOwner())) {
            return "redirect:/";
        }

        model.addAttribute("event", e);

        return "edit_event";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String handleEdit(Event e) {
        if (authService.getUserLoggedIn().equals(e.getOwner())) {
            eventRepo.save(e);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public String remove(@PathVariable Long id) {
        Event e = eventRepo.getOne(id);
        if (authService.getUserLoggedIn().equals(e.getOwner())) {
            eventRepo.delete(e);
        }

        return "redirect:/";
    }

}
