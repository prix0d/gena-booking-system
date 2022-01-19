package prixod.meeting_room.calendar;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.Date;

public class WeeklyCalendar {
    private final WeekPage weekPage = new WeekPage();
    public static final Calendar calendar = new Calendar("meets");
    private final CalendarSource calendarSource = new CalendarSource("Meets");
    private final ObservableList<CalendarSource> calendarSources = FXCollections.observableArrayList();

    public WeekPage getWeekPage() {
        return weekPage;
    }

    public WeeklyCalendar(){
        calendar.setStyle(com.calendarfx.model.Calendar.Style.STYLE7);
        calendarSource.getCalendars().add(calendar);
        calendarSources.add(calendarSource);
        Bindings.bindContentBidirectional(weekPage.getCalendarSources(), calendarSources);

        LocalDateTime t1 = LocalDateTime.now();
        LocalDateTime t2 = LocalDateTime.now().plusHours(2);
        Entry entry = new Entry<>();
        entry.setInterval(t1, t2);

        calendar.addEntry(new Entry<>("TEST"));
    }

    public static void Fill(){

    }
}
