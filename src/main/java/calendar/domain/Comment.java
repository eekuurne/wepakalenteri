
package calendar.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Comment extends AbstractPersistable<Long> {

    @NotNull
    @ManyToOne
    @JoinColumn
    private Event event;
    @NotNull
    @ManyToOne
    @JoinColumn
    private Account poster;
    @NotNull
    @Length(min = 1, max = 255)
    private String message;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date posted;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Account getPoster() {
        return poster;
    }

    public void setPoster(Account poster) {
        this.poster = poster;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }
    
    
}
