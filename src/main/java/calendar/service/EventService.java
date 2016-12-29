package calendar.service;

import calendar.domain.Account;
import org.springframework.stereotype.Service;
import calendar.domain.Event;
import calendar.domain.Participation;
import calendar.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventService {
    
    @Autowired
    private ParticipationRepository partRepo;
    
    public boolean isParticipatingIn(Account a, Event e) {
        Participation p = partRepo.findByEventAndAccount(e, a);
        Account owner = e.getOwner();
        
        if (owner.equals(a) || p != null) {
            return true;
        }
        
        return false;
    }
}
