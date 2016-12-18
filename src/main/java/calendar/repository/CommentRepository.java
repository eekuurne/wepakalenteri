
package calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Comment;
import calendar.domain.Event;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByEventOrderByPostedAsc(Event e);
}
