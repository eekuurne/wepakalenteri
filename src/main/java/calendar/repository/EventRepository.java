
package calendar.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import calendar.domain.Account;
import calendar.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    
//    @Query("SELECT e From Event e WHERE ")
//    public List<Event> findByParticipationAndDateBetweenXAndY(@Param("user") Account user, @Param("start") Date start, @Param("end") Date end);

}
