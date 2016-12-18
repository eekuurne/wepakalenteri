
package calendar.service;

import calendar.domain.Account;
import calendar.domain.Friendship;
import calendar.repository.FriendshipRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    private FriendshipRepository friendRepo;
    
    public List<Account> findFriends(Account a) {
        List<Account> friends = friendRepo.findFriendsByRequester(a);
        friends.addAll(friendRepo.findFriendsByTarget(a));
        return friends;
    }
    
    public List<Friendship> getFriendRequests(Account a) {
        return friendRepo.findFriendRequestsByTarget(a);
    }
    
    public void save(Friendship f) {
        friendRepo.save(f);
    }
}
