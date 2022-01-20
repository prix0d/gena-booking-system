package prixod.meeting_room.controllers;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import prixod.meeting_room.calendar.WeeklyCalendar;
import prixod.meeting_room.database.Database;
import prixod.meeting_room.database.Meet;
import prixod.meeting_room.views.EditForm;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    @FXML
    private VBox root;

    private Button cancelButton;
    private Button deleteButton;
    private Button saveButton;
    private ImageView plus;

    private ObservableList<EditForm> forms = FXCollections.observableArrayList();
    private ObservableList<Meet> meets = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EditForm ef1 = new EditForm(Database.entry);
        HBox hb1 = new HBox();
        hb1.getChildren().add(ef1.CreateLayout());
        hb1.setAlignment(Pos.CENTER);
        hb1.setMinHeight(400);
        hb1.setSpacing(100);
        root.getChildren().add(hb1);

        HBox hb2 = new HBox();
        hb2.setAlignment(Pos.CENTER);
        hb2.setSpacing(20);
        cancelButton = new Button("Cancel");
        deleteButton = new Button("Delete event");
        saveButton = new Button("Save");
        hb2.getChildren().addAll(cancelButton, deleteButton, saveButton);
        root.getChildren().add(hb2);
        hb2.setPadding(new Insets(50, 0, 0, 0));

        forms.add(ef1);

        plus = new ImageView(new Image("file:plus.png"));
        plus.setFitWidth(150);
        plus.setFitHeight(150);
        plus.setCursor(Cursor.HAND);

        hb1.getChildren().add(plus);
//        plus.setOnMouseClicked(event -> TestAddChild(hb1));
        plus.setOnMouseClicked(event -> AddEditForm(hb1));
    }

    private void AddEditForm(HBox root){
        var entry = new Entry<Meet>();
        var meet = new Meet(entry);
        entry.setUserObject(meet);
        EditForm ef = new EditForm(entry);
        root.getChildren().add(forms.size(), ef.CreateLayout());
        forms.add(ef);

        if (forms.size() == 3) root.getChildren().remove(3);
    }

    private void TestAddChild(HBox root){
        Label label = new Label("Test label");
        root.getChildren().add(label);
    }
}
