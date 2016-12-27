package calendar.controller;

import calendar.domain.Account;
import calendar.repository.AccountRepository;
import calendar.service.AuthenticationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A controller for handling accounts.
 */
@Controller
public class AccountController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping("/login")
    public String viewLogin(@RequestParam(required = false, value = "error") String error, @RequestParam(required = false, value = "username") String username, Model model) {
        if (authService.getUserLoggedIn() != null) {
            return "redirect:/logout";
        }

        if (error != null) {
            model.addAttribute("error", "Wrong username or password");
        }

        return "login";
    }

    @RequestMapping("/register")
    public String viewRegistration(@ModelAttribute Account account) {
        if (authService.getUserLoggedIn() != null) {
            return "redirect:/logout";
        }

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegistration(Model model, @Valid @ModelAttribute Account account, BindingResult bindingResult, @RequestParam String passwordConfirm) {
        if (authService.getUserLoggedIn() != null) {
            return "redirect:/logout";
        }

        if (!account.getPassword().equals(passwordConfirm)) {
            model.addAttribute("matchError", "passwords did not match");
        }
        if (accountRepo.findByUsername(account.getUsername()) != null) {
            model.addAttribute("uniqueError", "already in use");
        }
        if (bindingResult.hasErrors() || model.containsAttribute("matchError") || model.containsAttribute("uniqueError")) {
            model.addAttribute("username", account.getUsername());
            return "register";
        }

        if (!account.getRole().equals("USER")) {
            account.setRole("USER");
        }
        account.setPassword(encoder.encode(account.getPassword()));
        accountRepo.save(account);

        return "redirect:/login";
    }

}
