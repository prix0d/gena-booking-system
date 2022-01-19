package prixod.meeting_room.database;

import com.calendarfx.model.Entry;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Database {
    public static Meet meet;
    private static ArrayList<String> rooms;

    public static CopyOnWriteArrayList<Meet> data = new CopyOnWriteArrayList<>();
    private static String _login = "prixod";
    private static String _password = "12345";

    public static boolean CheckCreds(String login, String password){
        return Objects.equals(login, _login) && Objects.equals(password, _password);
    }

//    public void GenerateData(){
//        Entry e = new Entry();
//        e.lo
//    }
}
