package calendar.controller;

import calendar.domain.Account;
import calendar.domain.Event;
import calendar.domain.Participation;
import calendar.repository.AccountRepository;
import calendar.repository.EventRepository;
import calendar.repository.ParticipationRepository;
import calendar.service.AuthenticationService;
import calendar.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/events/{id}/participations")
public class ParticipationController {

    @Autowired
    private ParticipationRepository partRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private FriendService friendService;

    @RequestMapping(method = RequestMethod.POST)
    public String add(@PathVariable Long id, @RequestParam("user") String username) {
        Event e = eventRepo.findOne(id);
        Account userLoggedIn = authService.getUserLoggedIn();
        Account user = accountRepo.findByUsername(username);
        String additionalParams = "";
        if (userLoggedIn.equals(e.getOwner())
                && !userLoggedIn.equals(user)
                && partRepo.findByEventAndAccount(e, user) == null
                && friendService.findFriends().contains(user)) {

            Participation p = new Participation();
            p.setEvent(e);
            p.setAccount(user);
            p.setAccepted(false);
            partRepo.save(p);
        } else {
            additionalParams = "?failedUsername=" + username;
        }

        return "redirect:/events/" + e.getId() + additionalParams;
    }

    @RequestMapping("/confirm")
    public String confirm(@PathVariable Long id) {
        Event e = eventRepo.findOne(id);
        Account userLoggedIn = authService.getUserLoggedIn();
        Participation p = partRepo.findByEventAndAccount(e, userLoggedIn);
        if (p != null) {
            p.setAccepted(true);
            partRepo.save(p);
        }

        return "redirect:/profile";
    }

    @RequestMapping("/remove")
    public String remove(@PathVariable Long id, @RequestParam(required = false) String username) {
        Event e = eventRepo.findOne(id);
        Account userLoggedIn = authService.getUserLoggedIn();
        Participation p;
        if (username != null) {
            if (userLoggedIn.equals(e.getOwner())) {
                Account user = accountRepo.findByUsername(username);
                p = partRepo.findByEventAndAccount(e, user);
            } else {
                return "redirect:/";
            }
        } else {
            p = partRepo.findByEventAndAccount(e, userLoggedIn);
        }
        if (p != null) {
            partRepo.delete(p);
        }

        if (username != null) {
            return "redirect:/events/" + e.getId();
        }
        return "redirect:/profile";
    }
}
