
package calendar.repository;

import calendar.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Friendship;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query("SELECT f.target FROM Friendship f WHERE f.requester = :account AND f.accepted = true")
    public List<Account> findFriendsByRequester(@Param("account") Account account);
    
    @Query("SELECT f.requester FROM Friendship f WHERE f.target = :account AND f.accepted = true")
    public List<Account> findFriendsByTarget(@Param("account") Account account);
    
    @Query("SELECT f FROM Friendship f WHERE f.target = :account AND f.accepted = false")
    public List<Friendship> findFriendRequestsByTarget(@Param("account") Account account);
}
