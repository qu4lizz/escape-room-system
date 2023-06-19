package qu4lizz.escape_room.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qu4lizz.escape_room.model.economics.Reservation;
import qu4lizz.escape_room.model.game.Game;
import qu4lizz.escape_room.model.game.GameWrapper;
import qu4lizz.escape_room.model.game.Room;
import qu4lizz.escape_room.model.game.Team;
import qu4lizz.escape_room.model.quests.Quest;
import qu4lizz.escape_room.utils.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class GameMasterController implements Initializable {
    private static Stage stage;

    // General pane
    @FXML
    private AnchorPane generalPane;
    @FXML
    private AnchorPane generalViewPane;
    @FXML
    void generalOnClicked(MouseEvent event) {
        setActivePane(generalPane, generalViewPane);
    }
    // Check payments pane
    @FXML
    private AnchorPane checkPaymentsPane;
    @FXML
    private TableView<Game> paymentTable;
    @FXML
    private TableColumn<Game, Timestamp> datePayment; // TODO: STRING?
    @FXML
    private TableColumn<Game, BigDecimal> pricePayment;
    @FXML
    private TableColumn<Game, String> roomPayment;
    @FXML
    void checkPaymentsOnMouseClicked(MouseEvent event) {
        setActivePane(generalPane, checkPaymentsPane);

    }

    // Games pane
    @FXML
    private AnchorPane gamesPane;
    @FXML
    private AnchorPane gamesViewPane;
    @FXML
    void gamesOnClicked(MouseEvent event) {
        setActivePane(gamesPane, gamesViewPane);
    }
    // Start new game button and pane
    @FXML
    void startNewGameOnMouseClicked(MouseEvent event) {

    }
    @FXML
    private AnchorPane startNewGamePane;
    @FXML
    private ChoiceBox<String> chooseReservationChoiceBox;
    @FXML
    void startGameOnMouseClicked(MouseEvent event) {

    }
    @FXML
    private AnchorPane newGameControlPane;
    @FXML
    private ListView<String> gameLogListView;
    @FXML
    private VBox vboxReviews;
    @FXML
    void previousGamesOnMouseClicked(MouseEvent event) {

    }
    // Show games button and pane
    @FXML
    private AnchorPane showGamesPane;
    @FXML
    private TableView<Game> gamesTable;
    @FXML
    private TableColumn<Game, String> roomNameGames;
    @FXML
    private TableColumn<Game, Long> scoreGames;
    @FXML
    private TableColumn<Game, Timestamp> startTimeGames;
    @FXML
    private TableColumn<Game, String> teamNameGames;


    // Rooms pane
    @FXML
    private AnchorPane roomsPane;
    @FXML
    private AnchorPane roomsViewPane;
    @FXML
    void roomsOnClicked(MouseEvent event) {
        setActivePane(roomsPane, roomsViewPane);
    }
    // Add room button and pane
    @FXML
    void addRoomOnMouseClicked(MouseEvent event) {

    }
    @FXML
    private AnchorPane addRoomPane;
    @FXML
    private TextField roomNameTextField;
    @FXML
    private TextField maximumPlayersTextField;
    @FXML
    private TextField durationTextField;
    @FXML
    private ListView<Quest> questsNewRoomListView;
    @FXML
    void createNewRoomOnMouseClicked(MouseEvent event) {

    }
    // Delete room button and pane
    @FXML
    void deleteRoomOnMouseClicked(MouseEvent event) {

    }
    @FXML
    private AnchorPane deleteRoomPane;
    @FXML
    private ListView<Room> deleteRoomsListView;
    @FXML
    void deleteChosenRoomOnMouseClicked(MouseEvent event) {

    }


    // Reservations pane
    @FXML
    private AnchorPane reservationsPane;
    @FXML
    private AnchorPane reservationsViewPane;
    @FXML
    void reservationsOnClicked(MouseEvent event) {
        setActivePane(reservationsPane, reservationsViewPane);
    }
    // Add reservation button and pane
    @FXML
    void addReservationOnMouseClicked(MouseEvent event) {

    }
    @FXML
    private AnchorPane addReservationPane;
    @FXML
    private ChoiceBox<Room> chooseRoomForReservationChoiceBox;
    @FXML
    private ChoiceBox<Team> chooseTeamForReservationChoiceBox;
    @FXML
    private DatePicker reservationDatePicker;
    @FXML
    private TextField chooseTimeForReservationTextField;
    // Remove reservation button and pane
    @FXML
    void removeReservationOnMouseClicked(MouseEvent event) {

    }
    @FXML
    private AnchorPane deleteReservationPane;
    @FXML
    private ListView<Reservation> reservationsListView;
    @FXML
    void deleteChosenReservationOnMouseClicked(MouseEvent event) {

    }


    // Scoreboard pane
    @FXML
    private AnchorPane scoreboardPane;
    @FXML
    private AnchorPane scoreboardViewPane;
    @FXML
    void scoreboardOnClicked(MouseEvent event) {
        setActivePane(scoreboardPane, scoreboardViewPane);
    }
    @FXML
    private ChoiceBox<String> scoreboardRoomChoiceBox;
    @FXML
    private TableView<GameWrapper> scoreboardTable;
    @FXML
    private TableColumn<GameWrapper, Integer> positionScoreboardTableColumn;
    @FXML
    private TableColumn<GameWrapper, String> teamScoreboardTableColumn;
    @FXML
    private TableColumn<GameWrapper, Long> scoreScoreboardTableColumn;
    @FXML
    private TableColumn<GameWrapper, String> completedScoreboardTableColumn;

    // Quests pane
    @FXML
    private AnchorPane questsPane;
    @FXML
    private AnchorPane questsViewPane;
    @FXML
    void questsOnClicked(MouseEvent event) {
        setActivePane(questsPane, questsViewPane);
    }
    // Add quest button and pane
    @FXML
    void addQuestOnMouseClicked(MouseEvent event) {

    }
    @FXML
    private AnchorPane addQuestPane;
    @FXML
    private ChoiceBox<String> typeChoiceBox;
    @FXML
    private TextField nameOfQuestTextField;
    @FXML
    private TextField solutionOfQuestTextField;
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;
    @FXML
    private AnchorPane addPuzzlePane;
    @FXML
    void createNewQuestOnMouseClicked(MouseEvent event) {

    }
    // Delete quest button and pane
    @FXML
    void deleteQuestOnMouseClicked(MouseEvent event) {

    }
    @FXML
    private AnchorPane deleteQuestPane;
    @FXML
    private ListView<Quest> questsListView;
    @FXML
    void deleteChosenQuestOnMouseClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pricePayment.setCellValueFactory(new PropertyValueFactory<>("price"));
        datePayment.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        roomPayment.setCellValueFactory(new PropertyValueFactory<>("roomId"));

        roomNameGames.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        scoreGames.setCellValueFactory(new PropertyValueFactory<>("score"));
        startTimeGames.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        teamNameGames.setCellValueFactory(new PropertyValueFactory<>("teamName"));

        scoreboardRoomChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // TODO: update scoreboard table by new room value
        });
        teamScoreboardTableColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        scoreScoreboardTableColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        positionScoreboardTableColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        completedScoreboardTableColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String text, boolean empty) {
                super.updateItem(text, empty);
                if (empty || text == null) {
                    setText("");
                } else {
                    GameWrapper game = getTableView().getItems().get(getIndex());
                    String completionStatus = game.isFinished() ? "YES" : "NO";
                    setText(completionStatus);
                }
            }
        });


        scoreboardTable.getSortOrder().add(positionScoreboardTableColumn);
        positionScoreboardTableColumn.setSortType(TableColumn.SortType.ASCENDING);

        scoreboardTable.sort();

        typeChoiceBox.getItems().addAll("LOCK", "PUZZLE");
        difficultyChoiceBox.getItems().addAll("BEGINNER", "INTERMEDIATE", "ADVANCED", "EXPERT", "MASTER");
        typeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("LOCK")) {
                addPuzzlePane.setVisible(false);
            }
            else {
                addPuzzlePane.setVisible(true);
            }
        });

    }


    // Refactor method
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

        checkPaymentsPane.setVisible(false);
        startNewGamePane.setVisible(false);
        newGameControlPane.setVisible(false);
        showGamesPane.setVisible(false);
        addRoomPane.setVisible(false);
        deleteRoomPane.setVisible(false);
        addReservationPane.setVisible(false);
        deleteReservationPane.setVisible(false);
        addQuestPane.setVisible(false);
        addPuzzlePane.setVisible(false);
        deleteQuestPane.setVisible(false);

        paneView.setVisible(true);
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
    void questsListOnMouseClicked(MouseEvent event) {

    }



    @FXML
    void roomsListOnMouseClicked(MouseEvent event) {

    }




    public static void showStage() throws IOException {
        stage = new Stage();
        Utils.initStage(stage, "gamemaster_controller.fxml");
    }

}
