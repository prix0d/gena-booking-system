package prixod.meeting_room.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import prixod.meeting_room.MainApp;
import prixod.meeting_room.SceneChanger;
import prixod.meeting_room.database.Database;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button logInButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox showPasswordCheckbox;
    @FXML
    private VBox root;
    private Label errorLabel;


    public void initialize(URL location, ResourceBundle resources) {
        loginField.setText("prixod");
        passwordField.setText("12345");
        logInButton.setOnAction(event -> HandleLogin());
        errorLabel = new Label();
        errorLabel.setTextFill(Paint.valueOf("red"));
        root.getChildren().add(errorLabel);
    }

    private void HandleLogin(){
        if(!Database.CheckCreds(loginField.getText(), passwordField.getText())){
            errorLabel.setText("Invalid login data");
        }
        else SceneChanger.ChangeScene((Stage) logInButton.getScene().getWindow(), "main");
    }
}
