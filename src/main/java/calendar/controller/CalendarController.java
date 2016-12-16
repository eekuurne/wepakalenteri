package calendar.controller;

import calendar.domain.Account;
import calendar.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import calendar.service.EventService;
import calendar.service.InitializationService;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalendarController {
    
    @Autowired
    private EventService eventService;
    @Autowired
    private AccountRepository accountRepo;
    
    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String list(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepo.findByUsername(auth.getName());
        if (startDate == null || endDate == null) {
            startDate = new Date(System.currentTimeMillis());
            endDate = new Date(System.currentTimeMillis() + InitializationService.dayInMillis * 28);
        }
        model.addAttribute("events", eventService.list(account, startDate, endDate));
        return "calendar";
    }
}
