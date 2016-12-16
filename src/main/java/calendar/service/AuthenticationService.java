
package calendar.service;

import calendar.domain.Account;
import calendar.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    @Autowired
    private AccountRepository accountRepo;
    
    public Account getUserLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account a = accountRepo.findByUsername(auth.getName());
        
        return a;
    }
    
}
