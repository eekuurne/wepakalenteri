package calendar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Basic user. Has information about the users role.
 *
 */
@Entity
public class Account extends AbstractPersistable<Long> {

    @Column(unique = true)
    @NotNull
    @Length(min = 4, max = 25)
    private String username;
    @NotNull
    @Length(min = 6, max = 255)
    private String password;
    @NotNull
    private String role;
//    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    private List<Event> events;
//    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
//    private List<Participation> participating;
//    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
//    private List<Friendship> friendships;
//    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
//    private List<Comment> comments;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public List<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<Event> events) {
//        this.events = events;
//    }
}
