
package calendar.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Friendship can be either a friend request (if accepted == false) or
 * a friend relation between two accounts (if accepted == true).
 * The requester is the account that made the request so the friend request should
 * only be shown on the target account.
 */
@Entity
public class Friendship extends AbstractPersistable<Long> {
    
    @NotNull
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account requester;
    @NotNull
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account target;
    @NotNull
    private boolean accepted;

    public Account getRequester() {
        return requester;
    }

    public void setRequester(Account account1) {
        this.requester = account1;
    }

    public Account getTarget() {
        return target;
    }

    public void setTarget(Account account2) {
        this.target = account2;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    
    
}
