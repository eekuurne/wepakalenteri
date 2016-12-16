
package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import server.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByUsername(@Param("username") String username);
}
