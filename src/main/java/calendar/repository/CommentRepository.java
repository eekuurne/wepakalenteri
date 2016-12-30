package calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Comment;
import calendar.domain.Event;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Finds all comments related to an event.
     * 
     * @param e event
     * @return comments related to the event
     */
    public List<Comment> findByEventOrderByPostedAsc(Event e);
}
