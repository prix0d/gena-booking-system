package prixod.meeting_room.database;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import prixod.meeting_room.calendar.WeeklyCalendar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {
    private static ObservableList<String> rooms = FXCollections.observableArrayList();
    public static void FillDatabase(){

        for (int i = 0; i < 5; i++) {
            rooms.add("Room " + (i+1));
        }
        Database.rooms = rooms;

        Date now = new Date();
        now.setTime(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        var start = localDateTime.minusDays(14);
        var end = localDateTime.plusDays(14);

        var intervalStart = start;
        while (intervalStart.isBefore(end)){
            var duration = ThreadLocalRandom.current().nextInt(30, 300 + 1);
            var intervalEnd = intervalStart.plusMinutes(duration);
            var interval = new Interval(intervalStart, intervalEnd);
            CreateEntry(interval);
            intervalStart = intervalEnd.plusMinutes(ThreadLocalRandom.current().nextInt(0, 1440 + 1));
        }
    }

    private static void CreateEntry(Interval interval){
        Entry<Meet> entry = new Entry<>(rooms.get(ThreadLocalRandom.current().nextInt(0, 4 + 1)), interval);
        entry.setLocation(rooms.get(ThreadLocalRandom.current().nextInt(0, 4 + 1)));
        Meet meet = new Meet(entry);
        int participantsCount = ThreadLocalRandom.current().nextInt(2, 8 + 1);
        for (int i = 0; i < participantsCount; i++) {
            String participant = "Participant " + ThreadLocalRandom.current().nextInt(0, 9999 + 1);
            meet.participants.add(participant);
        }
        entry.setUserObject(meet);

        Database.data.add(meet);
        WeeklyCalendar.calendar.addEntry(entry);
    }
}
