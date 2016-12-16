
package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

}
