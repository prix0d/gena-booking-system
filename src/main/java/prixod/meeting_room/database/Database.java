package prixod.meeting_room.database;

import com.calendarfx.model.Entry;
import javafx.collections.ObservableList;
import prixod.meeting_room.calendar.WeeklyCalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

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

//    private List<Entry<Meet>> GetCloseEvents(Entry<Meet> entry){
//        Meet meet = entry.getUserObject();
//    }
}
