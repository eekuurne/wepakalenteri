package calendar.service;

import calendar.domain.Account;
import calendar.domain.Day;
import calendar.domain.Event;
import calendar.domain.Week;
import calendar.repository.EventRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that is used to produce days that are chronologically filled with
 * events. Gives only complete weeks as result.
 * Needs refactoring.
 * 
 * @see calendar.domain.Day
 * @see calendar.domain.Week
 */
@Service
public class DayService {

    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private AuthenticationService authService;

    private final Long dayInMillis = InitializationService.dayInMillis;

    /**
     * Generates days to fill the given gap.
     * If events exist for a day they are included with the day.
     * 
     * @param start Start date
     * @param end End date
     * @return List of complete weeks filled with days potentially filled with events
     */
    public List<Week> generateAndPopulateDays(Date start, Date end) {
        List<Week> weeks = new ArrayList<>();
        Calendar c = Calendar.getInstance();

        start.setHours(0);
        start.setMinutes(0);
        start.setSeconds(0);
        end.setHours(0);
        end.setMinutes(0);
        end.setSeconds(0);

        //Make start a full week
        c.setTime(start);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        while (dayOfWeek != 2) {
            start.setTime(start.getTime() - dayInMillis);
            c.setTime(start);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        }

        //Make end a full week
        c.setTime(end);
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        while (dayOfWeek != 1) {
            end.setTime(end.getTime() + dayInMillis);
            c.setTime(end);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        }
        //For the next while loop
        end.setTime(end.getTime() + dayInMillis);

        //Populate weeks and days
        Account user = authService.getUserLoggedIn();
        List<Event> eventsByStartTime = eventRepo.findByParticipationAndStartTimeBetweenXAndY(user, start, end);
        List<Event> eventsByEndTime = eventRepo.findByParticipationAndEndTimeBetweenXAndY(user, start, end);

        Week week = new Week();
        dayOfWeek = 0;
        int startListIndex = 0;
        int startListSize = eventsByStartTime.size();
        int endListIndex = 0;
        int endListSize = eventsByEndTime.size();

        while (start.before(end)) {
            Day day = new Day();

            //This is to delink the dates
            day.setDate(new Date(start.getTime()));
            
            boolean startListHasSuitable = true;
            boolean endListHasSuitable = true;
            
            while (true) {
                Event e = null;
                if (startListIndex < startListSize) {
                    e = eventsByStartTime.get(startListIndex);
                }
                Event e2 = null;
                if (endListIndex < endListSize) {
                    e2 = eventsByEndTime.get(endListIndex);
                }
                if (e != null
                        && e.getStartTime().after(start)
                        && e.getStartTime().before(new Date(start.getTime() + dayInMillis))) {
                    if (e2 != null && e.getStartTime().after(e2.getEndTime())) {
                    } else {
                        day.addEvent(e);
                        startListIndex++;
                        continue;
                    }
                } else {
                    startListHasSuitable = false;
                }
                if (e2 != null
                        && e2.getEndTime().after(start)
                        && e2.getEndTime().before(new Date(start.getTime() + dayInMillis))) {
                    day.addEvent(e2);
                    endListIndex++;
                } else {
                    endListHasSuitable = false;
                }

                if (!startListHasSuitable && !endListHasSuitable) {
                    week.setDay(dayOfWeek, day);
                    dayOfWeek++;
                    start.setTime(start.getTime() + dayInMillis);
                    break;
                }
            }
            if (dayOfWeek > 6) {
                dayOfWeek = 0;
                weeks.add(week);
                week = new Week();
            }
        }

        return weeks;
    }

}
