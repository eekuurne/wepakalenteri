
package calendar.controller;

import calendar.service.AuthenticationService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A controller for handling accounts.
 */
@Controller
public class AccountController {

    @Autowired
    private AuthenticationService authService;
    
    @RequestMapping("/login")
    public String viewLogin(@RequestParam(required = false) String error) {//, Model model) {
        if (authService.getUserLoggedIn() != null) {
            return "redirect:/logout";
        }
//        List<String> errors = new ArrayList<>();
//        if (error != null) {
//            errors.add(error);
//        }
//        model.addAttribute("errors", errors);
        
        return "login";
    }
}
