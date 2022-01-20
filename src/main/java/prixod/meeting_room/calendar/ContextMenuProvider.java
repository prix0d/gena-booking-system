package prixod.meeting_room.calendar;

import com.calendarfx.view.DateControl;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import prixod.meeting_room.MainApp;
import prixod.meeting_room.SceneChanger;

import java.io.IOException;

public class ContextMenuProvider extends com.calendarfx.view.ContextMenuProvider {
    @Override
    protected ContextMenu getWeekDayViewMenu(DateControl.ContextMenuParameter param) {
        var menu = super.getWeekDayViewMenu(param);

        menu.getItems().remove(1,5);
        menu.getItems().get(0).setOnAction(action -> {

            Stage stage = (Stage) menu.getScene().getWindow();
            SceneChanger.ChangeScene(stage, "edit");
        });

        return menu;
    }
}
