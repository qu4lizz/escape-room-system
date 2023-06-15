package qu4lizz.escape_room.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import qu4lizz.escape_room.utils.Utils;

import java.io.IOException;

public class GameMasterController {
    private static Stage stage;

    @FXML
    private AnchorPane gamesPane;
    @FXML
    private AnchorPane gamesViewPane;

    @FXML
    private AnchorPane generalPane;
    @FXML
    private AnchorPane generalViewPane;

    @FXML
    private AnchorPane questsPane;
    @FXML
    private AnchorPane questsViewPane;

    @FXML
    private AnchorPane reservationsPane;
    @FXML
    private AnchorPane reservationsViewPane;

    @FXML
    private AnchorPane roomsPane;
    @FXML
    private AnchorPane roomsViewPane;

    @FXML
    private AnchorPane scoreboardPane;
    @FXML
    private AnchorPane scoreboardViewPane;
    @FXML
    private ChoiceBox<String> scoreboardRoomChoiceBox;

    @FXML
    void gamesOnClicked(MouseEvent event) {
        setActivePane(gamesPane, gamesViewPane);
    }

    @FXML
    void generalOnClicked(MouseEvent event) {
        setActivePane(generalPane, generalViewPane);
    }

    @FXML
    void questsOnClicked(MouseEvent event) {
        setActivePane(questsPane, questsViewPane);
    }

    @FXML
    void reservationsOnClicked(MouseEvent event) {
        setActivePane(reservationsPane, reservationsViewPane);
    }

    @FXML
    void roomsOnClicked(MouseEvent event) {
        setActivePane(roomsPane, roomsViewPane);
    }

    @FXML
    void scoreboardOnClicked(MouseEvent event) {
        setActivePane(scoreboardPane, scoreboardViewPane);
        // TODO: scoreboardRoomChoiceBox.getItems().addAll(rooms);
    }

    // #6a6a6a active,  #5a5a5c inactive
    private void setActivePane(AnchorPane pane, AnchorPane paneView) {
        gamesPane.setStyle("-fx-background-color: #5a5a5c");
        generalPane.setStyle("-fx-background-color: #5a5a5c");
        questsPane.setStyle("-fx-background-color: #5a5a5c");
        reservationsPane.setStyle("-fx-background-color: #5a5a5c");
        roomsPane.setStyle("-fx-background-color: #5a5a5c");
        scoreboardPane.setStyle("-fx-background-color: #5a5a5c");

        pane.setStyle("-fx-background-color: #6a6a6a");

        gamesViewPane.setVisible(false);
        generalViewPane.setVisible(false);
        questsViewPane.setVisible(false);
        reservationsViewPane.setVisible(false);
        roomsViewPane.setVisible(false);
        scoreboardViewPane.setVisible(false);

        paneView.setVisible(true);
    }

    @FXML
    void addQuestOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void addReservationOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void addRoomOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void checkPaymentsOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void deleteQuestOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void deleteRoomOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void modifyQuestOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void modifyReservationOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void modifyRoomOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void previousGamesOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void questsListOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void removeReservationOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void roomsListOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void startNewGameOnMouseClicked(MouseEvent event) {

    }


    public static void showStage() throws IOException {
        stage = new Stage();
        Utils.initStage(stage, "gamemaster_controller.fxml");
    }

}
