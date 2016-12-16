
package calendar.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import calendar.domain.Account;
import calendar.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    
    @Query("SELECT e"
            + " FROM Event e"
            + " WHERE e.owner = :user"
            + " AND (e.startTime BETWEEN :start AND :end"
            + " OR e.endTime BETWEEN :start AND :end)")
    public List<Event> findByOwnerAndDateBetweenXAndY(@Param("user") Account user, @Param("start") Date start, @Param("end") Date end);
    
    @Query("SELECT e"
            + " FROM Participation p"
            + " INNER JOIN p.event e"
            + " WHERE (e.startTime BETWEEN :start AND :end"
            + " OR e.endTime BETWEEN :start AND :end)"
            + " AND p.account = :user")
    public List<Event> findByParticipationAndDateBetweenXAndY(@Param("user") Account user, @Param("start") Date start, @Param("end") Date end);

}
