package prixod.meeting_room.controllers;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import prixod.meeting_room.MainApp;
import prixod.meeting_room.SceneChanger;
import prixod.meeting_room.calendar.WeeklyCalendar;
import prixod.meeting_room.database.Database;
import prixod.meeting_room.database.Meet;
import prixod.meeting_room.views.EditForm;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    @FXML
    private VBox root;
    private HBox formRoot;

    private Button cancelButton;
    private Button saveButton;
    private ImageView plus;

    private ObservableList<EditForm> forms = FXCollections.observableArrayList();
    private ObservableList<Meet> meets = FXCollections.observableArrayList();
    private ArrayList<Box> boxes = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EditForm ef1;
        if (Database.entry != null) {
            ef1 = new EditForm(Database.entry);
        }
        else {
            ef1 = new EditForm();
        }
        HBox hb1 = new HBox();
        formRoot = hb1;
        VBox node = ef1.CreateLayout();
        hb1.getChildren().add(node);
        boxes.add(new Box(node, ef1));
        hb1.setAlignment(Pos.CENTER);
        hb1.setMinHeight(400);
        hb1.setSpacing(100);
        root.getChildren().add(hb1);

        HBox hb2 = new HBox();
        hb2.setAlignment(Pos.CENTER);
        hb2.setSpacing(20);
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            Database.meet = null;
            SceneChanger.ChangeScene((Stage) root.getScene().getWindow(), "main");
        });
        saveButton = new Button("Save");
        saveButton.setOnAction(event -> Save());

        hb2.getChildren().addAll(cancelButton, saveButton);
        root.getChildren().add(hb2);
        hb2.setPadding(new Insets(50, 0, 0, 0));

        forms.add(ef1);

        plus = new ImageView(new Image("file:plus.png"));
        plus.setFitWidth(150);
        plus.setFitHeight(150);
        plus.setCursor(Cursor.HAND);

        hb1.getChildren().add(plus);
        plus.setOnMouseClicked(event -> AddEditForm(hb1));
    }

    private class Box{
        public VBox node;
        public EditForm form;

        public Box(VBox node, EditForm form){
            this.node = node;
            this.form = form;
        }

        public Box(){

        }
    }

    private void AddEditForm(HBox root){
        var entry = new Entry<Meet>("Room 1");
        var meet = new Meet(entry);
        entry.setUserObject(meet);
        EditForm ef = new EditForm(entry);
        VBox node = ef.CreateLayout();
        root.getChildren().add(forms.size(), node);
        boxes.add(new Box(node, ef));
        forms.add(ef);

        if (forms.size() == 3) root.getChildren().remove(plus);
    }

    private void Save(){
        for (int i = 0; i < boxes.size(); i++) {
            var box = boxes.get(i);
            var form = box.form;
            if (form.Validate()){
                System.out.println(Database.data.size());
                if(Database.TryUpdateEntry(form)) {
                    System.out.println(Database.data.size());
                    formRoot.getChildren().remove(box.node);
                    forms.remove(box.form);
                    formRoot.getChildren().remove(box.node);
                }
                if (forms.size() == 0){
                    SceneChanger.ChangeScene((Stage) root.getScene().getWindow(), "main");
                }
            }
        }
    }
}
