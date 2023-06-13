package qu4lizz.escape_room;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import qu4lizz.escape_room.controller.SignInController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static final String TEXT_COLOR = "#344E41";
    public static final String BACKGROUND_COLOR = "#DAD7CD";
    public static final String FIELD_COLOR = "#A3B18A";
    public static final String ICON = "controller/icon/escape.png";
    public static final String TITLE = "Escape Room System";
    public static final String PATH = "controller/";
    public static final String FXML_PATH = PATH + "fxml/";

    public static Image icon;

    @Override
    public void start(Stage stage) throws IOException {
        icon = new Image(Application.class.getResource(ICON).toExternalForm());
        SignInController.showStage();
    }

    public static void main(String[] args) {
        launch();
    }
}
