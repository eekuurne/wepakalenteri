package calendar.controller;

import calendar.service.AuthenticationService;
import calendar.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private FriendService friendService;
    
    @ResponseBody
    @RequestMapping("/test")
    public String helloWorld() {
        return friendService.findFriends(authService.getUserLoggedIn()).toString();
    }

    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/calendar";
    }
}
