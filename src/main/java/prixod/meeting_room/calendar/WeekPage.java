package prixod.meeting_room.calendar;

import com.calendarfx.model.Entry;
import com.calendarfx.view.EntryViewBase;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Screen;
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
            Entry<Meet> entry = (Entry<Meet>) entryView.getEntry();

            var editOption = new MenuItem("Edit");
            editOption.setOnAction(event -> {
                Database.meet = (Meet) entry.getUserObject();
                Database.entry = (Entry<Meet>) entry;
                Stage stage = (Stage) this.getScene().getWindow();
                try {
                    Parent pane = FXMLLoader.load(MainApp.class.getResource("edit.fxml"));
                    stage.setScene(new Scene(pane));
                    stage.setMaximized(true);
                    stage.show();

                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                    stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SceneChanger.ChangeScene(stage, "edit");

            });
            var deleteOption = new MenuItem("Delete");
            deleteOption.setOnAction(event -> {
                System.out.println(Database.data);
                Database.Remove((Entry<Meet>) entry);
                System.out.println(Database.data);
            });

            ContextMenu contextMenu = new ContextMenu(editOption, deleteOption);
            return contextMenu;
        });
    }
}
