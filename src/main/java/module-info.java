module prixod.meeting_room {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.calendarfx.view;

    requires java.prefs;

    opens prixod.meeting_room to javafx.fxml;
    exports prixod.meeting_room;
    exports prixod.meeting_room.controllers;
    opens prixod.meeting_room.controllers to javafx.fxml;
}