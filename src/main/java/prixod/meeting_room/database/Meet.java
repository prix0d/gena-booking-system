package prixod.meeting_room.database;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meet {
    public String id;
    public String name;
    public String location;
    public Integer duration;

    public ObservableList<String> participants = FXCollections.observableArrayList();

    public LocalDateTime start;
    public LocalDateTime end;

    public Meet(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Meet(Entry<Meet> entry){
        this.id = entry.getId();
        this.name = entry.getTitle();
        this.location = entry.getLocation();
        LocalDateTime start = entry.getStartDate().atTime(entry.getStartTime());
        this.start = start;
        LocalDateTime end = entry.getEndDate().atTime(entry.getEndTime());
        this.end = end;
        Interval interval = new Interval(start, end);
        this.duration = Integer.getInteger(Long.toString(interval.getDuration().toMinutes()));
    }
}
