
package calendar.controller;

import calendar.repository.AccountRepository;
import calendar.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles friend related requests.
 */
@Controller
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private AccountRepository accountRepo;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewFriends(Model model) {
        model.addAttribute("friends", friendService.findFriends());
        model.addAttribute("requesters", friendService.getFriendRequesters());
        model.addAttribute("pending", friendService.getPendingRequests());
        
        return "friends";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addFriend(@RequestParam("username") String username) {
        friendService.addFriend(accountRepo.findByUsername(username));
        
        return "redirect:/friends";
    }
    
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeFriend(@RequestParam("username") String username) {
        friendService.removeFriend(accountRepo.findByUsername(username));
        
        return "redirect:/friends";
    }
}
