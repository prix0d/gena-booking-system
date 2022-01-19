package prixod.meeting_room.calendar;

import com.calendarfx.model.Entry;
import com.calendarfx.view.EntryViewBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import prixod.meeting_room.MainApp;
import prixod.meeting_room.SceneChanger;
import prixod.meeting_room.controllers.MainController;
import prixod.meeting_room.database.Database;
import prixod.meeting_room.database.Meet;

import java.io.IOException;
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
                Database.meet = (Meet) entry.getUserObject();
                Database.entry = (Entry<Meet>) entry;
                Stage stage = new Stage();
                try {
                    Parent pane = FXMLLoader.load(MainApp.class.getResource("edit.fxml"));
                    stage.setScene(new Scene(pane));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit.fxml"));
//                try {
//                    Parent parent = (Parent) fxmlLoader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                SceneChanger.ChangeScene(stage, "edit");

            });
            var deleteOption = new MenuItem("Delete");
            deleteOption.setOnAction(event -> entry.removeFromCalendar());




            ContextMenu contextMenu = new ContextMenu(editOption, deleteOption);
            return contextMenu;
        });
//        setEntryDetailsCallback(param -> new EntryDetailsCa);
    }
}
