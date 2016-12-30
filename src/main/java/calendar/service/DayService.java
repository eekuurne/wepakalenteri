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
 * events. Gives only complete weeks as result. Needs refactoring.
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

    //private final Long dayInMillis = InitializationService.dayInMillis;
    /**
     * Generates days to fill the given gap. If events exist for a day they are
     * included with the day.
     *
     * @param start Start date
     * @param end End date
     * @return List of complete weeks filled with days potentially filled with
     * events
     */
    public List<Week> generateAndPopulateDays(Date start, Date end, boolean skipWeekStreching) {
        List<Week> weeks = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        //Make sure that the whole days are used and get rid of millis.
        start.setHours(0);
        start.setMinutes(0);
        start.setSeconds(0);
        start.setTime((start.getTime() / 1000) * 1000);
        end.setHours(0);
        end.setMinutes(0);
        end.setSeconds(0);
        end.setTime((end.getTime() / 1000) * 1000);

        if (!skipWeekStreching) {
            makeStartAndEndFullWeeks(calendar, start, end);

            //For the next while loop.
            //end.setTime(end.getTime() + dayInMillis);
            end.setDate(end.getDate() + 1);
        }

        //Find needed events
        Account user = authService.getUserLoggedIn();
        List<Event> eventsByStartTime = eventRepo.findByParticipationAndStartTimeBetweenXAndY(user, start, end);
        List<Event> eventsByEndTime = eventRepo.findByParticipationAndEndTimeBetweenXAndY(user, start, end);

        //Populate weeks and days
        Week week = new Week();
        int dayOfWeek = 0;
        int startListIndex = 0;
        int startListSize = eventsByStartTime.size();
        int endListIndex = 0;
        int endListSize = eventsByEndTime.size();

        while (start.before(end)) {
            Day day = new Day();

            //This is to delink the dates. Otherwise all the events have the
            //same date.
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
                Date dummy = new Date(start.getTime());
                dummy.setDate(start.getDate() + 1);

                if (e != null
                        //&& e.getStartTime().after(start)
                        && e.getStartTime().getTime() >= start.getTime()
                        //&& e.getStartTime().before(new Date(start.getTime() + dayInMillis))) {
                        && e.getStartTime().before(dummy)) {
                    if (e2 != null && e.getStartTime().after(e2.getEndTime())) {
                    } else {
                        Event dummyE = new Event(e);
                        dummyE.setTitle(dummyE.getTitle() + " - Start");
                        day.addEvent(dummyE);
                        //day.addEvent(e);
                        startListIndex++;
                        continue;
                    }
                } else {
                    startListHasSuitable = false;
                }
                if (e2 != null
                        //&& e2.getEndTime().after(start)
                        && e2.getEndTime().getTime() >= start.getTime()
                        //&& e2.getEndTime().before(new Date(start.getTime() + dayInMillis))) {
                        && e2.getEndTime().before(dummy)) {
                    Event dummyE2 = new Event(e2);
                    dummyE2.setTitle(dummyE2.getTitle() + " - End");
                    day.addEvent(dummyE2);
                    //day.addEvent(e2);
                    endListIndex++;
                } else {
                    endListHasSuitable = false;
                }

                if (!startListHasSuitable && !endListHasSuitable) {
                    week.setDay(dayOfWeek, day);
                    dayOfWeek++;
                    //start.setTime(start.getTime() + dayInMillis);
                    start.setDate(start.getDate() + 1);
                    break;
                }
            }
            if (dayOfWeek > 6) {
                dayOfWeek = 0;
                weeks.add(week);
                week = new Week();
            }
        }

        if (skipWeekStreching) {
            weeks.add(week);
        }

        return weeks;
    }

    public List<Week> generateAndPopulateDays(Date start, Date end) {
        return generateAndPopulateDays(start, end, false);
    }

    //Americans and their "sunday is the first day of the week" FeelsBadMan
    private void makeStartAndEndFullWeeks(Calendar calendar, Date start, Date end) {
        calendar.setTime(start);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //while (dayOfWeek != 2) {
        while (dayOfWeek != 2) {
            //start.setTime(start.getTime() - dayInMillis);
            start.setDate(start.getDate() - 1);
            calendar.setTime(start);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        }

        calendar.setTime(end);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //while (dayOfWeek != 1) {
        while (dayOfWeek != 1) {
            //end.setTime(end.getTime() + dayInMillis);
            end.setDate(end.getDate() + 1);
            calendar.setTime(end);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        }
    }

    /**
     * Returns a single day with it's events.
     *
     * @param date date
     * @return a day with associated events
     */
    public Day getSingleDay(Date date) {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        Date end = new Date(date.getTime());
        end.setDate(end.getDate() + 1);

        Week week = generateAndPopulateDays(date, end, true).get(0);
        Day day = week.getDays()[0];

        return day;
    }

}
