package calendar.controller;

import calendar.domain.Day;
import calendar.repository.AccountRepository;
import calendar.repository.CommentRepository;
import calendar.repository.EventRepository;
import calendar.repository.ParticipationRepository;
import calendar.service.AuthenticationService;
import calendar.service.DayService;
import calendar.service.EventService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for testing stuff.
 */
@Profile("default")
@Controller
public class TestController {

    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private EventService eventService;
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private ParticipationRepository partRepo;
    @Autowired
    private DayService dayService;

    /**
     * Test URL to listen.
     * 
     * @return 
     */
    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        Day d = dayService.getSingleDay(new Date(System.currentTimeMillis()));
        System.out.println(d);
        System.out.println(d.getDate());
        System.out.println(d.getEvents());
        
        return "see logs";
    }

}
