package prixod.meeting_room.views;

import com.calendarfx.model.Entry;
import com.calendarfx.view.TimeField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import prixod.meeting_room.database.Database;
import prixod.meeting_room.database.Meet;

import java.util.ArrayList;

public class EditForm extends Node {
    private Entry<Meet> entry;
    private Meet meet;
    private ObservableList<String> participants;
    public TextField title;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TimeField startTimePicker;
    public TimeField endTimePicker;
    public ChoiceBox<String> roomChoiceBox;
    public ListView<String> participantsList;
    public Button participantRemoveButton;
    public Button participantAddButton;

    public EditForm(Entry<Meet> entry){
        this.entry = entry;
        this.meet = (Meet) entry.getUserObject();
        this.participants = FXCollections.observableArrayList();
        this.participants.addAll(meet.participants);
        LoadParams();
        CreateLayout();
    }

    private void LoadParams(){
        title = new TextField(entry.getTitle());
        startDatePicker = new DatePicker(entry.getStartDate());
        endDatePicker = new DatePicker(entry.getEndDate());
        startTimePicker = new TimeField();
        startTimePicker.setValue(entry.getStartTime());
        endTimePicker = new TimeField();
        endTimePicker.setValue(entry.getEndTime());
        roomChoiceBox = new ChoiceBox<>(Database.rooms);
        participantsList = new ListView<>(meet.participants);
    }

    public Node CreateLayout(){
        startDatePicker.setMinSize(150, 25);
        endDatePicker.setMinSize(150, 25);
        startTimePicker.setMinSize(150, 25);
        endTimePicker.setMinSize(150, 25);

        var root = new VBox();
        root.setMinSize(300, 400);
        root.setMaxSize(300, 400);
        title.setFont(Font.font(18));
        root.getChildren().add(title);

        HBox hbox1 = new HBox();

        VBox vb1 = new VBox();
        vb1.getChildren().add(CreateLabel("Start date"));
        vb1.getChildren().add(startDatePicker);
        vb1.getChildren().add(CreateLabel("Start time"));
        vb1.getChildren().add(startTimePicker);
        VBox vb2 = new VBox();
        vb2.getChildren().add(CreateLabel("End date"));
        vb2.getChildren().add(endDatePicker);
        vb2.getChildren().add(CreateLabel("End time"));
        vb2.getChildren().add(endTimePicker);
        hbox1.getChildren().addAll(vb1, vb2);

        root.getChildren().add(hbox1);

        Label lb1 = CreateLabel("Participants");
        lb1.setPadding(new Insets(20,0,0,0));
        root.getChildren().add(lb1);
        root.getChildren().add(new ListView<>());

        HBox hb2 = new HBox();
        hb2.setSpacing(10);
        hb2.setPadding(new Insets(10,0,0,0));
        TextField tf = new TextField();
        tf.setMinWidth(150);
        tf.setMaxWidth(150);
        participantRemoveButton = new Button("Remove");
        participantAddButton = new Button("Add");
        hb2.getChildren().addAll(tf, participantAddButton, participantRemoveButton);

        root.getChildren().add(hb2);

        return root;
    }

    private Label CreateLabel(String text){
        var label = new Label(text);
        label.setFont(Font.font(18));
        return label;
    }

    private Label CreateLabel(String text, Long fontSize){
        var label = new Label(text);
        label.setFont(Font.font(fontSize));
        return label;
    }
}
