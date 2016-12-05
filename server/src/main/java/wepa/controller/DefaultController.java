package wepa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    
    @ResponseBody
    @RequestMapping("/hello")
    public String handleDefault() {
        return "Hello World!";
    }
}
