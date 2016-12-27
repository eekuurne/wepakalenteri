package calendar.service;

import calendar.domain.Account;
import calendar.domain.Event;
import calendar.repository.ParticipationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipationService {
    
    @Autowired
    private ParticipationRepository partRepo;
    
    public List<Account> getParticipants(Event e) {
        return partRepo.findParticipantsByEvent(e, true);
    }
    
    public List<Account> getPendingParticipants(Event e) {
        return partRepo.findParticipantsByEvent(e, false);
    }
}
