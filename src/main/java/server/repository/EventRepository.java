
package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
