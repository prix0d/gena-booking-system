package prixod.meeting_room.views;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.TimeField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import prixod.meeting_room.database.Database;
import prixod.meeting_room.database.Meet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class EditForm extends Node {
    public Entry<Meet> entry;
    public Meet meet;
    public ObservableList<String> participants;
    public TextField titleTextField;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TimeField startTimePicker;
    public TimeField endTimePicker;
    public ChoiceBox<String> roomChoiceBox;
    public ListView<String> participantsListView;
    public TextField participantTextField;
    public Button participantRemoveButton;
    public Button participantAddButton;
    public Label errorLabel;
    public VBox layout;

    public EditForm(Entry<Meet> entry){
        LoadParams(entry);
        layout = CreateLayout();
        Database.entry = null;
    }

    public EditForm(){
        var entry = new Entry<Meet>("Room 1");
        Meet meet = new Meet(entry);
        entry.setUserObject(meet);
        LoadParams(entry);
        layout = CreateLayout();
        Database.entry = null;
    }

    private void LoadParams(Entry<Meet> entry){
        this.entry = entry;
        try {
            this.meet = (Meet) entry.getUserObject();
        }
        catch (Error err){
            this.meet = new Meet(entry);
        }
        this.participants = FXCollections.observableArrayList(meet.participants);
        titleTextField = new TextField(entry.getTitle());
        startDatePicker = new DatePicker(entry.getStartDate());
        endDatePicker = new DatePicker(entry.getEndDate());
        startTimePicker = new TimeField();
        startTimePicker.setValue(entry.getStartTime());
        endTimePicker = new TimeField();
        endTimePicker.setValue(entry.getEndTime());
        roomChoiceBox = new ChoiceBox<>(Database.rooms);
        roomChoiceBox.setValue(entry.getTitle());
//        else roomChoiceBox.setValue(entry.getTitle());
        participantsListView = new ListView<>(this.participants);
        participantsListView.setMaxHeight(150);
    }

    public VBox CreateLayout(){
        startDatePicker.setMinSize(140, 25);
        endDatePicker.setMinSize(140, 25);
        startTimePicker.setMinSize(140, 25);
        endTimePicker.setMinSize(140, 25);

        endDatePicker.setShowWeekNumbers(false);
        startDatePicker.setShowWeekNumbers(false);

        startDatePicker.setMaxSize(140, 25);
        endDatePicker.setMaxSize(140, 25);
        startTimePicker.setMaxSize(140, 25);
        endTimePicker.setMaxSize(140, 25);

        var root = new VBox();
        root.getChildren().add(CreateLabel("Location"));
        root.getChildren().add(roomChoiceBox);
        roomChoiceBox.setMinWidth(290);
        root.setPadding(new Insets(10));
        root.setMinSize(310, 410);
        root.setMaxSize(310, 1000);
//        titleTextField.setFont(Font.font(18));
//        root.getChildren().add(titleTextField);

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
        root.getChildren().add(participantsListView);

        HBox hb2 = new HBox();
        hb2.setSpacing(10);
        hb2.setPadding(new Insets(10,0,0,0));
        participantTextField = new TextField();
        participantTextField.setMinWidth(150);
        participantTextField.setMaxWidth(150);
        participantRemoveButton = new Button("Remove");
        participantAddButton = new Button("Add");
        participantAddButton.setOnAction(event -> AddParticipant());
        participantRemoveButton.setOnAction(event -> RemoveParticipant());
        hb2.getChildren().addAll(participantTextField, participantAddButton, participantRemoveButton);

        root.getChildren().add(hb2);
        root.setStyle("-fx-border-color: gray; -fx-border-radius: 10; -fx-background-radius: 12;");

        errorLabel = new Label();
        errorLabel.setTextFill(Paint.valueOf("red"));
        errorLabel.setVisible(false);
        root.getChildren().add(errorLabel);
        root.setStyle("-fx-background-color: white; -fx-background-radius: 15");

        return root;
    }

    private Label CreateLabel(String text){
        var label = new Label(text);
        label.setFont(Font.font(18));
        return label;
    }

    private void AddParticipant(){
        String participant = participantTextField.getText();
        if (participant.length() > 0) {
            participants.add(participant);
        }
        participantTextField.setText("");
    }

    private void RemoveParticipant(){
        List<String> selected = participantsListView.getSelectionModel().getSelectedItems();
        participants.removeAll(selected);
    }

    public boolean Validate(){
        String error = "";

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        LocalTime startTime = startTimePicker.getValue();
        LocalTime endTime = endTimePicker.getValue();

        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);


        if (endDate.isBefore(startDate)) error += "\nInvalid dates";
        else if (endDate.equals(startDate)){
            if (endTime.isBefore(startTime)) error += "\nInvalid time";
            var duration = (endTime.toSecondOfDay() - startTime.toSecondOfDay()) / 60;
            if (duration < 30) error += "\nToo short event duration";
        }
        else {
            var duration = new Interval(startDateTime, endDateTime).getDuration().getSeconds() / 60;
            if (duration < 30 ) error += "\nToo short event duration";
            if (duration > 1440 ) error += "\nToo long event duration";
        }
        if (participants.size() < 2) error += "\nToo few participants";
        if (titleTextField.getText().isEmpty()) error += "\nInvalid title";

        if (error.length() > 0){
            errorLabel.setVisible(true);
            errorLabel.setText(error);
            return false;
        }
        else errorLabel.setVisible(false);
        return true;
    }

    public void UpdateEntry(){
        entry.setTitle(roomChoiceBox.getValue());
//        entry.setLocation(roomChoiceBox.getValue());
        entry.setInterval(LocalDateTime.of(startDatePicker.getValue(), startTimePicker.getValue()), LocalDateTime.of(endDatePicker.getValue(), endTimePicker.getValue()));
    }

    public void UpdateMeet(){
        meet.Update(this);
    }

    public Entry<Meet> CreateEntry(){
        Entry<Meet> entry = new Entry<>(roomChoiceBox.getValue(), new Interval(LocalDateTime.of(
                startDatePicker.getValue(), startTimePicker.getValue()), LocalDateTime.of(endDatePicker.getValue(),
                endTimePicker.getValue())));
        entry.setUserObject(meet);
        return entry;
    }
}
