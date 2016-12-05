
package wepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.domain.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

}
