package calendar.service;

import calendar.domain.Account;
import calendar.domain.Friendship;
import calendar.repository.FriendshipRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service that makes handling friendships a bit more intuitive than
 * FriendshipRepository.
 *
 * @see calendar.repository.FriendshipRepository
 */
@Service
public class FriendService {

    @Autowired
    private FriendshipRepository friendRepo;
    @Autowired
    private AuthenticationService authService;

    /**
     * A method for finding the friends of the user logged in.
     *
     * @return The friends of current user
     */
    public List<Account> findFriends() {
        Account usrLoggedIn = authService.getUserLoggedIn();
        List<Account> friends = friendRepo.findFriendsByRequester(usrLoggedIn);
        friends.addAll(friendRepo.findFriendsByTarget(usrLoggedIn));
        return friends;
    }

    /**
     * Finds out who wants to be friends with the user logged in.
     *
     * @return Accounts who want to be friends with the user logged in
     */
    public List<Account> getFriendRequesters() {
        return friendRepo.findRequestersByTarget(authService.getUserLoggedIn());
    }

    /**
     * Creates a new friend request between the user logged in and the user
     * given as a parameter. If a request already exists (where the user logged
     * in is the target), accepts the request.
     *
     * @param otherUser The other user
     */
    public void addFriend(Account otherUser) {
        Account userLoggedIn = authService.getUserLoggedIn();
        
        if (userLoggedIn.equals(otherUser)) {
            return;
        }
        
        Friendship friendRequest = friendRepo.findRequestByTargetAndRequester(userLoggedIn, otherUser);
    
        if (friendRequest != null) {
            if (friendRequest.isAccepted()) {
                return;
            }
            friendRequest.setAccepted(true);
            friendRepo.save(friendRequest);
            return;
        }

        friendRequest = friendRepo.findRequestByTargetAndRequester(otherUser, userLoggedIn);

        if (friendRequest == null) {
            friendRequest = new Friendship();
            friendRequest.setRequester(userLoggedIn);
            friendRequest.setTarget(otherUser);
            friendRequest.setAccepted(false);
            
            friendRepo.save(friendRequest);
        }
    }

    /**
     * Removes a friendship between the user logged in and the user given as a
     * parameter.
     * 
     * @param otherUser User to be removed from the logged in users friends
     */
    public void removeFriend(Account otherUser) {
        Account userLoggedIn = authService.getUserLoggedIn();

        Friendship friendship = friendRepo.findRequestByTargetAndRequester(userLoggedIn, otherUser);
        if (friendship != null) {
            friendRepo.delete(friendship);
            return;
        }
        friendship = friendRepo.findRequestByTargetAndRequester(otherUser, userLoggedIn);
        if (friendship != null) {
            friendRepo.delete(friendship);
        }
    }
    
    /**
     * Finds all pending requests made by the logged in user.
     * 
     * @return Pending requests made by the logged in user
     */
    public List<Account> getPendingRequests() {
        return friendRepo.findPendingByRequester(authService.getUserLoggedIn());
    }
}
