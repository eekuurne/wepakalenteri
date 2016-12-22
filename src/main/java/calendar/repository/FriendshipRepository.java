
package calendar.repository;

import calendar.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Friendship;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * A repository for handling friends and friend requests.
 */
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    /**
     * Finds friends for the user from friendships where the requester is the
     * user.
     * 
     * @param account User to find friends for
     * @return Users friends (at least some of them)
     */
    @Query("SELECT f.target FROM Friendship f WHERE f.requester = :account AND f.accepted = true")
    public List<Account> findFriendsByRequester(@Param("account") Account account);
    
    /**
     * Finds friends for the user from friendships where the target is the
     * user.
     * 
     * @param account User to find friends for
     * @return Users friends (at least some of them)
     */
    @Query("SELECT f.requester FROM Friendship f WHERE f.target = :account AND f.accepted = true")
    public List<Account> findFriendsByTarget(@Param("account") Account account);
    
    /**
     * Finds friend requests made for the user.
     * 
     * @param account User to find friend requests for
     * @return Requesters
     */
    @Query("SELECT f.requester FROM Friendship f WHERE f.target = :account AND f.accepted = false")
    public List<Account> findRequestersByTarget(@Param("account") Account account);
    
    public Friendship findRequestByTargetAndRequester(Account target, Account requester);
    
    /**
     * Finds accounts that have pending friend requests from the user given as
     * a parameter.
     * 
     * @param account requester
     * @return Users that have pending requests
     */
    @Query("SELECT f.target FROM Friendship f WHERE f.requester = :account AND f.accepted = false")
    public List<Account> findPendingByRequester(@Param("account") Account account);
}
