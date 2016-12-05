
package wepa.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<Long> {
    
    @NotNull
    private Account owner;
    //private List<Account> participants;
    @NotNull
    @Length(min = 2, max = 40)
    private String title;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @NotNull
    @Length(min = 4, max = 40)
    private String Place;
    @Length(max = 200)
    private String Description;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String Place) {
        this.Place = Place;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}
