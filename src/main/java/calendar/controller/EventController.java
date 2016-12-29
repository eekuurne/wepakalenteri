package calendar.controller;

import calendar.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import calendar.domain.Event;
import calendar.repository.CommentRepository;
import calendar.repository.EventRepository;
import calendar.service.AuthenticationService;
import calendar.service.EventService;
import calendar.service.ParticipationService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A controller for handling events.
 */
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private EventService eventService;
    @Autowired
    private ParticipationService partService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping("/{id}")
    public String view(@PathVariable Long id, Model model, @RequestParam(required = false) String failedUsername) {
        Event event = eventRepo.findOne(id);
        Account userLoggedIn = authService.getUserLoggedIn();
        if (event == null || !eventService.isParticipatingIn(userLoggedIn, event)) {
            return "redirect:/";
        }

        if (failedUsername != null) {
            model.addAttribute("failedUsername", failedUsername);
            model.addAttribute("inviteError", "that user is not your friend");
        }
        model.addAttribute("userLoggedIn", userLoggedIn);
        model.addAttribute("event", event);
        model.addAttribute("comments", commentRepo.findByEventOrderByPostedAsc(event));
        model.addAttribute("participants", partService.getParticipants(event));
        model.addAttribute("pendingParticipants", partService.getPendingParticipants(event));

        return "event";
    }

    @RequestMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("startTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        return "newevent";
    }
    
    @RequestMapping(value = "/create/{date}")
    public String createToDate(Model model,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        model.addAttribute("startTime", new SimpleDateFormat("yyyy-MM-dd").format(date));
        return "newevent";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam String title,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
            @RequestParam String place,
            @RequestParam String description) {
        
        //Validoitava JS:llä
        
        Event event = new Event();
        event.setTitle(title);
        event.setPlace(place);
        event.setDescription(description);
        event.setOwner(authService.getUserLoggedIn());
        
        if (startTime != null) {
            event.setStartTime(startTime);
        } else {
            event.setStartTime(new Date(System.currentTimeMillis()));
        }
        if (endTime != null) {
            if (event.getStartTime().before(endTime)) {
                event.setEndTime(endTime);
            } else {
                event.setEndTime(event.getStartTime());
            }
        } else {
            event.setEndTime(event.getStartTime());
        }
        eventRepo.save(event);

        return "redirect:/events/" + event.getId();
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
    public String handleEdit(@Valid @RequestParam Event e) {
        if (authService.getUserLoggedIn().equals(e.getOwner())) {
            //Errorit?
            eventRepo.save(e);
        }

        return "redirect:/events/" + e.getId();
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
