package calendar.controller;

import calendar.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import calendar.service.InitializationService;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalendarController {
    
    @Autowired
    private DayService dayService;
    
    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String list(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date endDate) {
        if (startDate == null) {
            startDate = new Date(System.currentTimeMillis());
        }
        if (endDate == null) {
            endDate = new Date(startDate.getTime() + InitializationService.dayInMillis * 27);
        }
        model.addAttribute("weeks", dayService.generateAndPopulateDays(startDate, endDate));
        return "calendar";
    }
}
