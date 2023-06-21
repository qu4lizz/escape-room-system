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
        int type;
        String usernameInput = usernameTextField.getText();
        String passwordInput = passwordTextField.getText();
        if (usernameInput.equals("") || passwordInput.equals("")) {
            PopUpController.showStage("Sign In", "Enter valid parameters");
            return;
        }
        try {
            String result = SQLUtil.signIn(usernameInput, passwordInput);
            signIn = Boolean.parseBoolean(result.split("#")[0]);
            type = Integer.parseInt(result.split("#")[1]);
            if (signIn) {
                username = usernameInput;
                if (type == 0) {
                    AdminController.showStage();
                }
                else if (type == 1) {
                    GameMasterController.showStage();
                }
            } else {
                PopUpController.showStage("Sign In", "Enter valid parameters");
                return;
            }
        } catch (Exception ex) {
            PopUpController.showStage("Sign In", "Enter valid parameters");
            ex.printStackTrace();
            return;
        }

        GameMasterController.showStage();

        ((Stage)usernameTextField.getScene().getWindow()).close();
    }

    public static void showStage() throws IOException {
        stage = new Stage();
        Utils.initStage(stage, "signin_controller.fxml");
    }
}
