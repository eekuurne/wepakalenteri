
package calendar.service;

import calendar.domain.Account;
import calendar.repository.FriendshipRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    private FriendshipRepository friendRepo;
    
    public List<Account> findFriends(Account a) {
        List<Account> friends = friendRepo.findByAccount1(a);
        friends.addAll(friendRepo.findByAccount2(a));
        return friends;
    }
}
