package calendar.service;

import calendar.domain.Account;
import calendar.repository.AccountRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * A service used to find the current logged in user.
 *
 */
@Service
public class AuthenticationService {

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private HttpSession session;

    /**
     * Returns the logged in user. Invalidates the session if the user in the
     * current session cannot be found in the DB.
     *
     * @return Account that is related to the session
     */
    public Account getUserLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account a = accountRepo.findByUsername(auth.getName());

        if (a == null) {
            session.invalidate();
        }

        return a;
    }

}
