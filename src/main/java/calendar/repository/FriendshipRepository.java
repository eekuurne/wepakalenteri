
package calendar.repository;

import calendar.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import calendar.domain.Friendship;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query("SELECT f.account2 FROM Friendship f WHERE f.account1 = :account AND f.accepted = true")
    public List<Account> findByAccount1(@Param("account") Account account);
    
    @Query("SELECT f.account1 FROM Friendship f WHERE f.account2 = :account AND f.accepted = true")
    public List<Account> findByAccount2(@Param("account") Account account);
}
