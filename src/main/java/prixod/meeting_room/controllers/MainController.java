package prixod.meeting_room.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import prixod.meeting_room.calendar.CalendarView;
import prixod.meeting_room.calendar.WeeklyCalendar;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private BorderPane borderPane;

    private WeeklyCalendar calendar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calendar = new WeeklyCalendar();
        calendar.getWeekPage().setStyle("-fx-background-radius: 20;");
        borderPane.setPadding(new Insets(50));
        borderPane.setCenter(calendar.getWeekPage());
    }
}
