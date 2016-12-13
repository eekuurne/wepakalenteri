
package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByUsername(String username);
}
