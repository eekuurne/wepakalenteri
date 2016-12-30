
package calendar.controller;

import calendar.domain.Day;
import calendar.service.DayService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/days")
public class DayController {

    @Autowired
    private DayService dayService;
    
    @RequestMapping
    public String view(Model model, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Day day = dayService.getSingleDay(date);
        model.addAttribute("day", day);
        
        return "day";
    }
}
