package calendar.controller;

import calendar.domain.Day;
import calendar.domain.Week;
import calendar.repository.AccountRepository;
import calendar.repository.CommentRepository;
import calendar.repository.EventRepository;
import calendar.service.DayService;
import static calendar.service.InitializationService.dayInMillis;
import java.util.Date;
import java.util.List;
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
    private AccountRepository accountRepo;
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
        System.out.println(new Date(System.currentTimeMillis()));
        List<Week> weeks = dayService.generateAndPopulateDays(new Date(System.currentTimeMillis() - dayInMillis), new Date(System.currentTimeMillis() + dayInMillis * 27));
        System.out.println(weeks);
        for (Week week : weeks) {
            for (int i = 0; i < 7; i++) {
                Day day = week.getDays()[i];
                System.out.println(day.getDate());
                System.out.println(day.getEvents());
            }
        }
        return "See logs";
    }

}
