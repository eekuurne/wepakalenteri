
package calendar.controller;

import calendar.domain.Comment;
import calendar.repository.CommentRepository;
import calendar.service.AuthenticationService;
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
    private AuthenticationService authService;
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addComment(@RequestParam Comment comment) {
        comment.setPosted(new Date(System.currentTimeMillis()));
        comment.setPoster(authService.getUserLoggedIn());
        commentRepo.save(comment);
        
        return "redirect:/";
        //return "redirect:/events/" + comment.getEvent().getId();
    }

}
