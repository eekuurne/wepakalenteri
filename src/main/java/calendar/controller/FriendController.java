
package calendar.controller;

import calendar.domain.Account;
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
    
//    @RequestMapping(method = RequestMethod.GET)
//    public String viewFriends(Model model) {
//        model.addAttribute("friends", friendService.findFriends());
//        model.addAttribute("requesters", friendService.getFriendRequesters());
//        model.addAttribute("pending", friendService.getPendingRequests());
//        
//        return "friends";
//    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addFriend(Model model, @RequestParam("username") String username) {
        Account otherUser = accountRepo.findByUsername(username);
        if (otherUser == null) {
            return "redirect:/profile?failedFriendUsername=" + username;
        }
        friendService.addFriend(otherUser);
        
        return "redirect:/profile";
    }
    
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeFriend(@RequestParam("username") String username) {
        friendService.removeFriend(accountRepo.findByUsername(username));
        
        return "redirect:/profile";
    }
}
