
package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

}
