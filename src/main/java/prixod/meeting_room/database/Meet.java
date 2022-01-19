package prixod.meeting_room.database;

import com.calendarfx.model.Entry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meet {
    public String id;
    public String name;
    public String location;

    public ArrayList<String> participants = new ArrayList<>();

    public LocalDate start;
    public LocalDate end;

    public Meet(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Meet(Entry entry){
        this.id = entry.getId();
        this.name = entry.getTitle();
        this.location = entry.getLocation();
        this.start = entry.getStartDate();
        this.end = entry.getEndDate();
    }
}
