package prixod.meeting_room.database;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import prixod.meeting_room.views.EditForm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Meet {
    public String id;
    public String name;
    public String location;
    public Integer duration;

    public ObservableList<String> participants;

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
//        this.duration = Integer.getInteger(Long.toString(interval.getDuration().toMinutes()));
        participants = FXCollections.observableArrayList();
        entry.setUserObject(this);
    }

    public Meet(Entry<Meet> entry, ObservableList<String> participants){
        this.id = entry.getId();
        this.name = entry.getTitle();
        this.location = entry.getLocation();
        LocalDateTime start = entry.getStartDate().atTime(entry.getStartTime());
        this.start = start;
        LocalDateTime end = entry.getEndDate().atTime(entry.getEndTime());
        this.end = end;
        Interval interval = new Interval(start, end);
//        this.duration = Integer.getInteger(Long.toString(interval.getDuration().toMinutes()));
        this.participants = FXCollections.observableArrayList(participants);
        entry.setUserObject(this);
    }

    public Entry<Meet> ToEntry(){
        Entry<Meet> entry = new Entry<>(name, new Interval(start, end, TimeZone.getDefault().toZoneId()));
        entry.setLocation(location);
        entry.setId(id);
        entry.setUserObject(this);
        return entry;
    }

    public void Update(EditForm editForm){
        name = editForm.titleTextField.getText();
        location = editForm.roomChoiceBox.getValue();
        start = LocalDateTime.of(editForm.startDatePicker.getValue(), editForm.startTimePicker.getValue());
        end = LocalDateTime.of(editForm.endDatePicker.getValue(), editForm.endTimePicker.getValue());
        participants = FXCollections.observableArrayList(editForm.participants);
    }
}
