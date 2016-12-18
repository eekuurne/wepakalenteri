package calendar.controller;

import calendar.repository.CommentRepository;
import calendar.repository.EventRepository;
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

    /**
     * Test URL to listen.
     * 
     * @return 
     */
    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        return commentRepo.findByEventOrderByPostedAsc(eventRepo.findOne(new Long(1))).toString();
    }

}
