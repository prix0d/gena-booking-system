package prixod.meeting_room.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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


    public void initialize(URL location, ResourceBundle resources) {
//        screenController = new ScreenController();
        logInButton.setOnAction(event -> HandleLogin());
    }

    private void HandleLogin(){
        System.out.println("login handler");
//        if (Database.CheckCreds(loginField.getText(), passwordField.getText())) ChangeScene()
        SceneChanger.ChangeScene((Stage) logInButton.getScene().getWindow(), "main");
    }
}
