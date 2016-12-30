package calendar.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import calendar.domain.Account;
import calendar.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Deprecated
    @Query("SELECT e"
            + " FROM Event e"
            + " WHERE e.owner = :user"
            + " AND (e.startTime BETWEEN :start AND :end"
            + " OR e.endTime BETWEEN :start AND :end)"
            + " ORDER BY e.startTime ASC")
    public List<Event> findByOwnerAndDateBetweenXAndY(@Param("user") Account user, @Param("start") Date start, @Param("end") Date end);

    @Deprecated
    @Query("SELECT e"
            + " FROM Participation p"
            + " INNER JOIN p.event e"
            + " WHERE (e.startTime BETWEEN :start AND :end"
            + " OR e.endTime BETWEEN :start AND :end)"
            + " AND p.account = :user"
            + " ORDER BY e.startTime ASC")
    public List<Event> findByParticipationAndDateBetweenXAndY(@Param("user") Account user, @Param("start") Date start, @Param("end") Date end);

//        @Query("SELECT DISTINCT e"
//            + " FROM Participation p"
//            + " INNER JOIN p.event e"
//            + " WHERE (e.startTime BETWEEN :start AND :end)"
//            + " AND p.account = :user"
//            + " AND p.accepted = true"
//            + " OR e.owner = :user"
//            + " ORDER BY e.startTime ASC")
    /**
     * Finds all events that the user is either participating in or is the
     * owner. Compares Event.startTime to start and end.
     * 
     * @param user Account
     * @param start Start time
     * @param end End time
     * @return Events in the time frame
     */
    @Query("SELECT DISTINCT e"
            + " FROM Event e, Participation p"
            + " WHERE (e.startTime BETWEEN :start AND :end)"
            + " AND ((p.event = e"
            + " AND p.account = :user"
            + " AND p.accepted = true)"
            + " OR e.owner = :user)"
            + " ORDER BY e.startTime ASC")
    public List<Event> findByParticipationAndStartTimeBetweenXAndY(@Param("user") Account user, @Param("start") Date start, @Param("end") Date end);

//    @Query("SELECT DISTINCT e"
//            + " FROM Participation p"
//            + " INNER JOIN p.event e"
//            + " WHERE (e.endTime BETWEEN :start AND :end)"
//            + " AND p.account = :user"
//            + " AND p.accepted = true"
//            + " OR e.owner = :user"
//            + " ORDER BY e.endTime ASC")
    /**
     * Finds all events that the user is either participating in or is the
     * owner. Compares Event.endTime to start and end.
     * 
     * @param user Account
     * @param start Start time
     * @param end End time
     * @return Events in the time frame
     */
    @Query("SELECT DISTINCT e"
            + " FROM Event e, Participation p"
            + " WHERE (e.endTime BETWEEN :start AND :end)"
            + " AND ((p.event = e"
            + " AND p.account = :user"
            + " AND p.accepted = true)"
            + " OR e.owner = :user)"
            + " ORDER BY e.endTime ASC")
    public List<Event> findByParticipationAndEndTimeBetweenXAndY(@Param("user") Account user, @Param("start") Date start, @Param("end") Date end);
}
