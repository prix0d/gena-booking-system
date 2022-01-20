package prixod.meeting_room;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import prixod.meeting_room.database.DataGenerator;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {
    private static final Image logoImage = new Image(Objects.requireNonNull(MainApp.class.getResource("gena.png")).toString());
    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(logoImage);
        DataGenerator.FillDatabase();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Gena Booking");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}