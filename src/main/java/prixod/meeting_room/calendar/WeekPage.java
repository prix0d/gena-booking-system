package prixod.meeting_room.calendar;

import com.calendarfx.model.Entry;
import com.calendarfx.view.EntryViewBase;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import prixod.meeting_room.database.Database;

import java.util.ArrayList;
import java.util.List;

public class WeekPage extends com.calendarfx.view.page.WeekPage{
    public WeekPage(){
        super();
        init();
    }

    private void init(){
        setContextMenuCallback(new ContextMenuProvider());
        setEntryContextMenuCallback(param -> {
            var entryView = param.getEntryView();
            var control = param.getDateControl();
            var entry = entryView.getEntry();

            var editOption = new MenuItem("Edit");
            editOption.setOnAction(event -> {
                entry.setTitle("TTT");
//                System.out.println("edit");
//                System.out.println(entry);
                System.out.println(new Entry<>("Test"));
//                Database.meet =
            });
            var deleteOption = new MenuItem("Delete");
            deleteOption.setOnAction(event -> entry.removeFromCalendar());




            ContextMenu contextMenu = new ContextMenu(editOption, deleteOption);
            return contextMenu;
        });
//        setEntryDetailsCallback(param -> new EntryDetailsCa);
    }
}
