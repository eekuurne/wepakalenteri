package server.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import server.domain.Account;
import server.repository.AccountRepository;

@Controller
public class DefaultController {

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PasswordEncoder encoder;
    
    @PostConstruct
    public void initialize(){
        Account a = new Account();
        a.setUsername("User1");
        a.setPassword(encoder.encode("Password"));
        a.setRole("USER");
        accountRepo.save(a);
    }
    
    @ResponseBody
    @RequestMapping("/hello")
    public String handleDefault() {
        return "Hello World!";
    }
}
