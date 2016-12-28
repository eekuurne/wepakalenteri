package calendar.controller;

import calendar.repository.ParticipationRepository;
import calendar.service.AuthenticationService;
import calendar.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private ParticipationRepository partRepo;

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model, @RequestParam(required = false) String failedFriendUsername) {
        model.addAttribute("username", authService.getUserLoggedIn().getUsername());
        model.addAttribute("friends", friendService.findFriends());
        model.addAttribute("requesters", friendService.getFriendRequesters());
        model.addAttribute("pending", friendService.getPendingRequests());
        model.addAttribute("pendingParticipations", partRepo.findByAccountAndAccepted(authService.getUserLoggedIn(), false));
        if (failedFriendUsername != null) {
            model.addAttribute("failedFriendUsername", failedFriendUsername);
            model.addAttribute("addFriendError", "user not found");
        }

        return "profile";
    }
}
