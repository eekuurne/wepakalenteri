package calendar.controller;

import calendar.repository.AccountRepository;
import calendar.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private FriendService friendService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("friends", friendService.findFriends());
        model.addAttribute("requesters", friendService.getFriendRequesters());
        model.addAttribute("pending", friendService.getPendingRequests());
        
        return "profile";
    }
}
