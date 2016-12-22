
package calendar.domain;

/**
 * A container for days.
 * Used to populate the calendar.
 * 
 * @see calendar.domain.Day
 * @see calendar.service.DayService
 */
public class Week {
    
    private Day[] days;
    
    public Week() {
        days = new Day[7];
    }

    public Day[] getDays() {
        return days;
    }
    
    public void setDay(int index, Day day) {
        days[index] = day;
    }

}
