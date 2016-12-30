package calendar.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<Long> {

    public Event() {
    }

    public Event(Event e) {
        this.setId(e.getId());
        this.owner = e.getOwner();
        this.title = e.getTitle();
        this.description = e.getDescription();
        this.place = e.getPlace();
        this.startTime = e.getStartTime();
        this.endTime = e.getEndTime();
    }

    @NotNull
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account owner;
//    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
//    private List<Participation> participants;
    @NotNull
    @Length(max = 40)
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;
    @Length(max = 40)
    private String place;
    @Length(max = 200)
    private String description;
//    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
//    private List<Comment> comments;

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
        return place;
    }

    public void setPlace(String Place) {
        this.place = Place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

}
