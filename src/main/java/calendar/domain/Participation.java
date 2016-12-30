package calendar.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Participation is representation of a user participating (accepted == true) or
 * having an invitation (accepted == false) to a event. The user can visit the
 * events page in both circumstances but the event is only visible in the users
 * calendar after the invitation has been accepted.
 *
 */
@Entity
public class Participation extends AbstractPersistable<Long> {

    @NotNull
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Event event;
    @NotNull
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;
    @NotNull
    private boolean accepted;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

}
