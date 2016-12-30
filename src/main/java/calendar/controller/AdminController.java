package calendar.controller;

import calendar.domain.Account;
import calendar.repository.AccountRepository;
import calendar.service.AuthenticationService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping
    public String adminPage(Model model, @RequestParam(required = false) String failedUsername, HttpSession session) {
        authService.getUserLoggedIn();
        model.addAttribute("accounts", accountRepo.findAll());
        if (failedUsername != null) {
            model.addAttribute("failedUsername", failedUsername);
            model.addAttribute("userError", "user not found");
        }

        return "admin";
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public String deleteUser(@RequestParam String username) {
        Account a = accountRepo.findByUsername(username);
        if (a == null) {
            return "redirect:/admin?failedUsername=" + username;
        }
        accountRepo.delete(a);

        return "redirect:/admin";
    }
}
