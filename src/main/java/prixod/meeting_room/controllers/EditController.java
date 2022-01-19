package prixod.meeting_room.controllers;

import com.calendarfx.model.Interval;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import prixod.meeting_room.calendar.WeeklyCalendar;
import prixod.meeting_room.database.Database;
import prixod.meeting_room.database.Meet;
import prixod.meeting_room.views.EditForm;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EditForm ef1 = new EditForm(Database.entry);
        root.getChildren().add(ef1.CreateLayout());
    }
}
