
package calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

}
