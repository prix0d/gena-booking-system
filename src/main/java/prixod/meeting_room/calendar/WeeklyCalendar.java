package prixod.meeting_room.calendar;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import prixod.meeting_room.database.Database;
import prixod.meeting_room.database.Meet;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Consumer;

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
        calendar.addEventHandler(this::calendarHandler);
    }

    private void calendarHandler(CalendarEvent event) {
        var eventType = event.getEventType();
        var entry = (Entry<Meet>) event.getEntry();

        if (eventType.equals(CalendarEvent.ENTRY_CALENDAR_CHANGED)) {
            var calendar = event.getCalendar();
            if (calendar != null && entry.getUserObject() == null) {
                Meet meet = new Meet(entry);
                Database.data.add(meet);
            }
        }
    }
}
