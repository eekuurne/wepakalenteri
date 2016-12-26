
package calendar.repository;

import calendar.domain.Account;
import calendar.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    
    public Participation findByEventAndAccount(Event e, Account a);

}
