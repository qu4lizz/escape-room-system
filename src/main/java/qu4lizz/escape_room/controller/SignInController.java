package qu4lizz.escape_room.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import qu4lizz.escape_room.Application;
import qu4lizz.escape_room.utils.Utils;

import java.io.IOException;

public class SignInController {
    private static Stage stage;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void signInMouseClicked(MouseEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        GameMasterController.showStage();
        ((Stage)usernameTextField.getScene().getWindow()).close();
    }

    public static void showStage() throws IOException {
        stage = new Stage();
        Utils.initStage(stage, "signin_controller.fxml");
    }
}
