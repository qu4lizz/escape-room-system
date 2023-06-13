package qu4lizz.escape_room.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import qu4lizz.escape_room.Application;

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

    }

    public static void showStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(Application.FXML_PATH + "signin_controller.fxml"));

        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(Application.TITLE);
        stage.getIcons().add(Application.icon);
        stage.setScene(scene);
        stage.show();
    }
}
