package qu4lizz.escape_room.utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import qu4lizz.escape_room.Application;

import java.io.IOException;

public class Utils {
    public static void initStage(Stage stage, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(Application.FXML_PATH + fxml));

        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(Application.TITLE);
        stage.getIcons().add(Application.icon);
        stage.setScene(scene);
        stage.show();
    }
}
