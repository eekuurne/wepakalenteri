package calendar.repository;

import calendar.domain.Account;
import calendar.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Participation;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    public Participation findByEventAndAccountAndAccepted(Event e, Account a, boolean accepted);
    
    public Participation findByEventAndAccount(Event e, Account a);

    public Participation findByAccountAndAccepted(Account a, Boolean accepted);
    
    /**
     * Finds users that are participating in a specific event.
     * 
     * @param event Event
     * @param accepted Is the participation an invite or an actual participation
     * @return Accounts that are participating
     */
    @Query("SELECT p.account FROM Participation p WHERE p.event = :event AND p.accepted = :accepted")
    public List<Account> findParticipantsByEvent(@Param("event") Event event, @Param("accepted") boolean accepted);

}
