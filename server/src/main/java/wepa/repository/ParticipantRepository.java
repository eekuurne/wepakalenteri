
package wepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.domain.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
