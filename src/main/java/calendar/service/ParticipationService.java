package calendar.service;

import calendar.domain.Account;
import calendar.domain.Event;
import calendar.repository.ParticipationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service that makes handling participations a bit more intuitive.
 *
 */
@Service
public class ParticipationService {

    @Autowired
    private ParticipationRepository partRepo;

    /**
     * Finds users that are participating in a specific event.
     *
     * @param e Event
     * @return Participating users
     */
    public List<Account> getParticipants(Event e) {
        return partRepo.findParticipantsByEvent(e, true);
    }

    /**
     * Finds users that are invited to participate in a specific event.
     *
     * @param e Event
     * @return Invited users
     */
    public List<Account> getPendingParticipants(Event e) {
        return partRepo.findParticipantsByEvent(e, false);
    }
}
