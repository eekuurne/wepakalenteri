package calendar.controller;

import calendar.domain.Account;
import calendar.domain.Comment;
import calendar.domain.Event;
import calendar.repository.CommentRepository;
import calendar.repository.EventRepository;
import calendar.service.AuthenticationService;
import calendar.service.EventService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addComment(@RequestParam String message, @RequestParam Long eventId) {
        Account userLoggedIn = authService.getUserLoggedIn();
        Event e = eventRepo.findOne(eventId);
        if (e == null || !eventService.isParticipatingIn(userLoggedIn, e)) {
            return "redirect:/";
        }
        Comment comment = new Comment();
        comment.setMessage(message);
        comment.setEvent(e);
        comment.setPosted(new Date(System.currentTimeMillis()));
        comment.setPoster(userLoggedIn);
        commentRepo.save(comment);

        return "redirect:/events/" + e.getId();
    }

}
