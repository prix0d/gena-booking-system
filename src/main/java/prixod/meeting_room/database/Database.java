package prixod.meeting_room.database;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import javafx.collections.ObservableList;
import prixod.meeting_room.calendar.WeeklyCalendar;
import prixod.meeting_room.views.EditForm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Database {
    public static Meet meet;
    public static Entry<Meet> entry;
    public static ObservableList<String> rooms;

    public static CopyOnWriteArrayList<Meet> data = new CopyOnWriteArrayList<>();
    private static String _login = "prixod";
    private static String _password = "12345";

    public static boolean CheckCreds(String login, String password){
        return Objects.equals(login, _login) && Objects.equals(password, _password);
    }

    public static void Remove(Entry<Meet> entry){
        Meet meet = entry.getUserObject();
        data.remove(meet);
        entry.removeFromCalendar();
//        WeeklyCalendar.calendar.removeEntry(entry);
    }

    public static Boolean TryAddEntry(Entry<Meet> entry){
        Meet meet = entry.getUserObject();
        LocalDate entryStartDate = entry.getStartDate();
        LocalDate entryEndDate = entry.getEndDate();
        LocalTime entryStartTime = entry.getStartTime();
        LocalTime entryEndTime = entry.getEndTime();
        LocalDateTime entryStartDateTime = LocalDateTime.of(entryStartDate, entryStartTime);
        LocalDateTime entryEndDateTime = LocalDateTime.of(entryEndDate, entryEndTime);

        var closest = WeeklyCalendar.calendar.findEntries(entryStartDate, entryEndDate,
                ZoneId.systemDefault()).values();
        var events = new ArrayList<Entry<Meet>>();
        closest.forEach(eventList -> {
            eventList.forEach(event -> {
                events.add((Entry<Meet>) event);
            });
        });
        ArrayList<Entry<Meet>> events2 = new ArrayList<>(events);
        events.forEach(event -> {
            if (event.getTitle() == entry.getTitle()) events2.add(event);
        });
        if (events2.size() > 0){
            for (int i = 0; i < events2.size(); i++){
                Entry<Meet> event = events2.get(i);
                LocalDate eventStartDate = event.getStartDate();
                LocalDate eventEndDate = event.getEndDate();
                LocalTime eventStartTime = event.getStartTime();
                LocalTime eventEndTime = event.getEndTime();
                LocalDateTime eventStartDateTime = LocalDateTime.of(eventStartDate, eventStartTime);
                LocalDateTime eventEndDateTime = LocalDateTime.of(eventEndDate, eventEndTime);

                if (entry.intersects(event)) {
                    if (entry.getTitle() == event.getTitle()) return false;
                }
//                if (entryStartDateTime.isAfter(eventStartDateTime) && entryEndDateTime.isBefore(eventEndDateTime)) return false;
//                if (entryEndDateTime.isAfter(entryStartDateTime) || entryStartDateTime.isBefore(eventStartDateTime)) return false;
//            var duration = new Interval(startDateTime, endDateTime).getDuration().getSeconds() / 60;
            }
        }

        WeeklyCalendar.calendar.addEntry(entry);
        if (!data.contains(meet)) data.add(meet);
        return true;
    }

    public static Boolean TryUpdateEntry(EditForm editForm){
        Entry<Meet> entry = editForm.entry;
        Entry<Meet> newEntry = editForm.CreateEntry();
        if (Database.data.contains(entry.getUserObject())){
            Meet meet = entry.getUserObject();
            entry.removeFromCalendar();

            if (TryAddEntry(newEntry)) {
                meet.Update(editForm);
                meet.id = newEntry.getId();
                return true;
            }
            else {
                WeeklyCalendar.calendar.addEntry(entry);
                return false;
            }
        }
        else {
            editForm.UpdateEntry();
            return TryAddEntry(editForm.entry);
        }
    }

//    public static Entry<Meet> CreateEntry(EditForm editForm){
//        Entry<Meet> entry = new Entry<Meet>(editForm.titleTextField.getText(), new Interval(
//                LocalDateTime.of(editForm.startDatePicker.getValue(), editForm.startTimePicker.getValue()),
//                LocalDateTime.of(editForm.endDatePicker.getValue(), editForm.endTimePicker.getValue())));
//        entry.setLocation(editForm.roomChoiceBox.getValue());
//        Meet meet = new Meet(entry, editForm.participants);
//        entry.setUserObject(meet);
//        return entry;
//    }
}
