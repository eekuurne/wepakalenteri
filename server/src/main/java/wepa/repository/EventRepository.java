
package wepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
