package qu4lizz.escape_room.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import qu4lizz.escape_room.utils.SQLUtil;
import qu4lizz.escape_room.utils.Utils;

import java.io.IOException;

public class SignInController {
    private static Stage stage;
    public static String username;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void signInMouseClicked(MouseEvent event) throws IOException {
        boolean signIn;
        String type;
        String usernameInput = usernameTextField.getText();
        String passwordInput = passwordTextField.getText();
        // TODO: remove
        /*
        if (usernameInput.equals("") || passwordInput.equals("")) {
            PopUpController.showStage("Sign In", "Enter valid parameters");
            return;
        }*/

        usernameInput = "gm1";
        passwordInput = "gm1";
        String result = SQLUtil.signIn(usernameInput, passwordInput);
        if (result == null) {
            PopUpController.showStage("Sign In", "Enter valid parameters");
            return;
        }
        signIn = Boolean.parseBoolean(result.split("#")[0]);
        type = result.split("#")[1];
        if (signIn) {
            username = usernameInput;
            if (type.equals("ADMIN")) {
                AdminController.showStage();
            }
            else if (type.equals("GAMEMASTER")) {
                GameMasterController.showStage();
            }
        } else {
            PopUpController.showStage("Sign In", "Enter valid parameters");
            return;
        }

        ((Stage)usernameTextField.getScene().getWindow()).close();
    }

    public static void showStage() throws IOException {
        stage = new Stage();
        Utils.initStage(stage, "signin_controller.fxml");
    }
}
