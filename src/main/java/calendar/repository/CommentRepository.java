
package calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
