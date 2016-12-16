
package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
