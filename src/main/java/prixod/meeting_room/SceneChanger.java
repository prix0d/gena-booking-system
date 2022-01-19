package prixod.meeting_room;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class SceneChanger {
    public static void ChangeScene(Stage stage, String view) {
        view += ".fxml";
        try {
            Parent pane = FXMLLoader.load(MainApp.class.getResource(view));
            stage.getScene().setRoot(pane);
//            stage.setHeight(720);
//            stage.setWidth(1280);
            stage.setMaximized(true);
        }
        catch (Exception e){
            System.out.println(e);
        };
    }
}
