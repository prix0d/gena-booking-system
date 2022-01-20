package prixod.meeting_room.calendar;

import com.calendarfx.model.Entry;
import com.calendarfx.view.EntryViewBase;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.InputEvent;
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

        var weekDayViewFactory = getDetailedWeekView().getWeekView().getWeekDayViewFactory();

        getDetailedWeekView().getWeekView().setWeekDayViewFactory(param -> {
            var weekDayView = weekDayViewFactory.call(param);

            weekDayView.setEntryViewFactory(DayEntryView::new);

            return weekDayView;
        });

        setEntryEditPolicy(param -> {
            var editOperation = param.getEditOperation();

            return false;
        });

        setContextMenuCallback(param -> {
            var createOption = new MenuItem("Create Entry");
            createOption.setOnAction(event -> {
                Stage stage = (Stage) this.getScene().getWindow();
                try {
                    SceneChanger.ChangeScene(stage, "edit");

                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                    stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
                    Entry<Meet> entry = new Entry<Meet>();

                } catch (Error e) {
                    e.printStackTrace();
                }
            });
            ContextMenu contextMenu = new ContextMenu(createOption);
            return contextMenu;
        });

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
                    SceneChanger.ChangeScene(stage, "edit");

                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                    stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
                } catch (Error e) {
                    e.printStackTrace();
                }

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

        setEntryDetailsCallback(param -> {return null;});

        setEntryFactory(event -> null);
    }
}
