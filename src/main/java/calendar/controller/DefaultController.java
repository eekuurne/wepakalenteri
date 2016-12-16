package calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {
    
    @ResponseBody
    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
    
    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/calendar";
    }
}
