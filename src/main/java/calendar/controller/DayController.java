package calendar.controller;

import calendar.domain.Day;
import calendar.service.DayService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller that handles viewing contents of a single day.
 * 
 */
@Controller
@RequestMapping("/days")
public class DayController {

    @Autowired
    private DayService dayService;

    @RequestMapping("/{date}")
    public String view(Model model, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Day day = dayService.getSingleDay(date);
        model.addAttribute("day", day);

        return "day";
    }
}
