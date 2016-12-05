
package wepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
