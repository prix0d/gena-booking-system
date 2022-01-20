package prixod.meeting_room;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SceneChanger {
    public static void ChangeScene(Stage stage, String view) {
        view += ".fxml";
        try {
            Parent pane = FXMLLoader.load(MainApp.class.getResource(view));
            stage.getScene().setRoot(pane);
//            stage.setHeight(600);
//            stage.setWidth(800);
            stage.setMaximized(true);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
        }
        catch (Exception e){
            System.out.println(e);
        };
    }
}
