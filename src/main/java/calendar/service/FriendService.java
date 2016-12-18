
package calendar.service;

import calendar.domain.Account;
import calendar.domain.Friendship;
import calendar.repository.FriendshipRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service that makes handling friendships a bit more intuitive than FriendshipRepository.
 * 
 * @see calendar.repository.FriendshipRepository
 */
@Service
public class FriendService {

    @Autowired
    private FriendshipRepository friendRepo;
    
    /**
     * Method for finding the friends of an account.
     * 
     * @param account The account to find the friends for
     * @return The accounts that are the friends of the account given as parameter 
     */
    public List<Account> findFriends(Account account) {
        List<Account> friends = friendRepo.findFriendsByRequester(account);
        friends.addAll(friendRepo.findFriendsByTarget(account));
        return friends;
    }
    
    /**
     * Finds friend requests targeted for the account given as the parameter.
     * 
     * @param account The account to find requests for.
     * @return Open friend requests
     */
    public List<Friendship> getFriendRequests(Account account) {
        return friendRepo.findFriendRequestsByTarget(account);
    }
    
    /**
     * Saves/updates the friendship given as a parameter.
     * 
     * @param f friendship to save/update
     */
    public void save(Friendship f) {
        friendRepo.save(f);
    }
}
