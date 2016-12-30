package calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The default controller that redirects all 404 traffic to the main page.
 * 
 */
@Controller
public class DefaultController { 
    
    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/calendar";
    }
}
