
package calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

}
