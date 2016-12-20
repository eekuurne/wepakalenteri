
package calendar.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A container for events.
 * Used to populate the calendar.
 * 
 * @see calendar.domain.Week
 * @see calendar.service.DayService
 */
public class Day {
    
    private Date date;
    private List<Event> events;

    public Day() {
        events = new ArrayList<>();
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event e) {
        events.add(e);
    }

}
