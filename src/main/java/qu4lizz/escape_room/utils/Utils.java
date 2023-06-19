package qu4lizz.escape_room.utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import qu4lizz.escape_room.Application;
import qu4lizz.escape_room.model.game.Game;
import qu4lizz.escape_room.model.game.GameWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public static List<GameWrapper> gamesToWrapper(List<Game> games) {
        List<Game> sortedGamesByScore = games.stream().sorted(Comparator.comparing(Game::getScore)).toList();
        List<GameWrapper> wrapper = new ArrayList<>();

        for (int i = 1; i < sortedGamesByScore.size(); i++) {
            Game game = sortedGamesByScore.get(i);
            GameWrapper gameWrapper = new GameWrapper(game, i);
            wrapper.add(gameWrapper);
        }

        return wrapper;
    }
}
