package qu4lizz.escape_room.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qu4lizz.escape_room.data.*;
import qu4lizz.escape_room.data.mysql.*;
import qu4lizz.escape_room.model.economics.Reservation;
import qu4lizz.escape_room.model.game.Game;
import qu4lizz.escape_room.model.game.GameWrapper;
import qu4lizz.escape_room.model.game.Room;
import qu4lizz.escape_room.model.game.Team;
import qu4lizz.escape_room.model.info.GameLog;
import qu4lizz.escape_room.model.info.GameReview;
import qu4lizz.escape_room.model.quests.*;
import qu4lizz.escape_room.model.users.Player;
import qu4lizz.escape_room.utils.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GameMasterController implements Initializable {
    private static Stage stage;
    private GeneralDataAccess generalDataAccess = new GeneralDataAccessImpl();
    private GameDataAccess gameDataAccess = new GameDataAccessImpl();
    private QuestDataAccess questDataAccess = new QuestDataAccessImpl();
    private ReservationDataAccess reservationDataAccess = new ReservationDataAccessImpl();
    private RoomsDataAccess roomsDataAccess = new RoomsDataAccessImpl();

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
    void checkPaymentsOnMouseClicked(MouseEvent event) {
        setActivePane(generalPane, checkPaymentsPane);
        List<Game> games = gameDataAccess.getGames();
        paymentTable.getItems().clear();
        paymentTable.getItems().addAll(games);
    }
    @FXML
    private AnchorPane checkPaymentsPane;
    @FXML
    private TableView<Game> paymentTable;
    @FXML
    private TableColumn<Game, Timestamp> datePayment;
    @FXML
    private TableColumn<Game, BigDecimal> pricePayment;
    @FXML
    private TableColumn<Game, String> roomPayment;


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
        setActivePane(gamesPane, startNewGamePane);
        List<Reservation> reservations = reservationDataAccess.getReservations();
        chooseReservationChoiceBox.getItems().clear();
        chooseReservationChoiceBox.getItems().addAll(reservations);
    }
    @FXML
    private AnchorPane startNewGamePane;
    @FXML
    private ChoiceBox<Reservation> chooseReservationChoiceBox;
    private Reservation reservation;
    private GameLog gameLog;
    private GameReview gameReview;
    private Team team;
    private HashMap<String, TextField> reviewFields = new HashMap<>();
    @FXML
    void startGameOnMouseClicked(MouseEvent event) {
        reservation = chooseReservationChoiceBox.getValue();
        gameLog = new GameLog();
        List<Quest> quests = questDataAccess.getQuests();
        gameLogListView.getItems().clear();
        for (var quest : quests) {
            gameLog.add(quest.toString());
        }
        setActivePane(gamesPane, newGameControlPane);
    }
    @FXML
    private AnchorPane newGameControlPane;
    @FXML
    private ListView<String> gameLogListView;
    @FXML
    private VBox vboxReviews;
    @FXML
    void endGameOnMouseClicked(MouseEvent event) { // TODO: time left?
        gameLog.add("GAME ENDED");
        initVBox();
    }
    @FXML
    void addGameAfterFinishOnMouseClicked(MouseEvent event) {
        Timestamp startTime = reservation.getStartTime();
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        long score = Duration.between(startTime.toLocalDateTime(), endTime.toLocalDateTime()).getSeconds();
        Room room = roomsDataAccess.getRoom(reservation.getRoom());
        BigDecimal price = room.getPrice().multiply(BigDecimal.valueOf(team.getPlayers().length));

        Game game = new Game(room.getName(), startTime, endTime, score, reservation.getTeam(),
                SignInController.username, price);

        for (var elem : reviewFields.entrySet()) {
            // TODO: check if text is not empty
            GameReview review = new GameReview(elem.getKey(), elem.getValue().getText());
            game.addReview(review);
        }

        gameDataAccess.addGame(game);

        vboxReviews.getChildren().clear();


        setActivePane(gamesPane, gamesViewPane);
    }
    // Show games button and pane
    @FXML
    void previousGamesOnMouseClicked(MouseEvent event) {
        List<Game> games = gameDataAccess.getGames();
        gamesTable.getItems().clear();
        gamesTable.setItems(FXCollections.observableArrayList(games));
        setActivePane(gamesPane, showGamesPane);
    }
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
        setActivePane(roomsPane, addRoomPane);
        List<Quest> quests = questDataAccess.getQuests();
        questsNewRoomListView.getItems().clear();
        questsNewRoomListView.getItems().addAll(quests);
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
    private TextField priceTextField;
    @FXML
    private ListView<Quest> questsNewRoomListView;
    @FXML
    void createNewRoomOnMouseClicked(MouseEvent event) {
        String name = roomNameTextField.getText();
        int maximumPlayers = Integer.parseInt(maximumPlayersTextField.getText());
        Time duration = Time.valueOf(durationTextField.getText());
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceTextField.getText()));
        // TODO: check if all fields are filled
        List<Quest> quests = questsNewRoomListView.getSelectionModel().getSelectedItems();
        Room room = new Room(name, maximumPlayers, duration, price, new HashSet<>(quests));
        roomsDataAccess.addRoom(room);
        setActivePane(roomsPane, roomsViewPane);
    }
    // Delete room button and pane
    @FXML
    void deleteRoomOnMouseClicked(MouseEvent event) {
        setActivePane(roomsPane, deleteRoomPane);
        List<Room> rooms = roomsDataAccess.getRooms();
        deleteRoomsListView.getItems().clear();
        deleteRoomsListView.getItems().addAll(rooms);
    }
    @FXML
    private AnchorPane deleteRoomPane;
    @FXML
    private ListView<Room> deleteRoomsListView;
    @FXML
    void deleteChosenRoomOnMouseClicked(MouseEvent event) {
        Room room = deleteRoomsListView.getSelectionModel().getSelectedItem();
        roomsDataAccess.deleteRoom(room.getName());
        setActivePane(roomsPane, roomsViewPane);
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
        setActivePane(reservationsPane, addReservationPane);
        List<Room> rooms = roomsDataAccess.getRooms();
        chooseRoomForReservationChoiceBox.getItems().clear();
        chooseRoomForReservationChoiceBox.getItems().addAll(rooms);
        List<Team> teams = generalDataAccess.getTeams();
        chooseTeamForReservationChoiceBox.getItems().clear();
        chooseTeamForReservationChoiceBox.getItems().addAll(teams);
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
    @FXML
    void addNewReservationOnMouseClicked(MouseEvent event) {
        // TODO: check if all fields are filled

        Room room = chooseRoomForReservationChoiceBox.getSelectionModel().getSelectedItem();
        Team team = chooseTeamForReservationChoiceBox.getSelectionModel().getSelectedItem();
        LocalDate date = reservationDatePicker.getValue();
        LocalTime time = LocalTime.parse(chooseTimeForReservationTextField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        Timestamp timestamp = Timestamp.valueOf(dateTime);


        Reservation reservation = new Reservation(room.getName(), team.getName(), timestamp);

        reservationDataAccess.addReservation(reservation);

        setActivePane(reservationsPane, reservationsViewPane);
    }
    // Remove reservation button and pane
    @FXML
    void removeReservationOnMouseClicked(MouseEvent event) {
        List<Reservation> reservations = reservationDataAccess.getReservations();
        reservationsListView.getItems().clear();
        reservationsListView.getItems().addAll(reservations);
        setActivePane(reservationsPane, deleteReservationPane);
    }
    @FXML
    private AnchorPane deleteReservationPane;
    @FXML
    private ListView<Reservation> reservationsListView;
    @FXML
    void deleteChosenReservationOnMouseClicked(MouseEvent event) {
        Reservation reservation = reservationsListView.getSelectionModel().getSelectedItem();
        reservationDataAccess.deleteReservation(reservation);
        reservationsListView.getItems().remove(reservation);
    }


    // Scoreboard pane
    @FXML
    private AnchorPane scoreboardPane;
    @FXML
    private AnchorPane scoreboardViewPane;
    @FXML
    void scoreboardOnClicked(MouseEvent event) {
        List<Room> rooms = roomsDataAccess.getRooms();
        scoreboardRoomChoiceBox.getItems().clear();
        scoreboardRoomChoiceBox.getItems().addAll(rooms);
        setActivePane(scoreboardPane, scoreboardViewPane);
    }
    @FXML
    private ChoiceBox<Room> scoreboardRoomChoiceBox;
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
    String[] types = {"Puzzle", "Lock"};
    String[] difficulties = {"Beginner", "Intermediate", "Advanced", "Expert", "Master"};
    @FXML
    void addQuestOnMouseClicked(MouseEvent event) {
        setActivePane(questsPane, addQuestPane);
        typeChoiceBox.getItems().clear();
        typeChoiceBox.getItems().addAll(types);
        List<Inventory> inventories = generalDataAccess.getInventories();
        inventoryChoiceBox.getItems().clear();
        inventoryChoiceBox.getItems().addAll(inventories);
        difficultyChoiceBox.getItems().clear();
        difficultyChoiceBox.getItems().addAll(difficulties);
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
    private ChoiceBox<Inventory> inventoryChoiceBox;
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;
    @FXML
    private AnchorPane addPuzzlePane;
    @FXML
    void createNewQuestOnMouseClicked(MouseEvent event) {
        // TODO: check if all fields are filled
        String type = typeChoiceBox.getSelectionModel().getSelectedItem();
        String name = nameOfQuestTextField.getText();
        String solution = solutionOfQuestTextField.getText();
        Inventory inventory = inventoryChoiceBox.getSelectionModel().getSelectedItem();
        String difficulty = difficultyChoiceBox.getSelectionModel().getSelectedItem();
        Quest quest = null;
        if (type.equals("Puzzle")) {
            quest = new Puzzle(name, solution, Difficulty.valueOf(difficulty), null, inventory.getId());
        }
        else {
            quest = new Lock(name, solution,  null, inventory.getId());
        }
        questDataAccess.addQuest(quest);
    }
    // Delete quest button and pane
    @FXML
    void deleteQuestOnMouseClicked(MouseEvent event) {
        List<Quest> quests = questDataAccess.getQuests();
        questsListView.getItems().clear();
        questsListView.getItems().addAll(quests);
        setActivePane(questsPane, deleteQuestPane);
    }
    @FXML
    private AnchorPane deleteQuestPane;
    @FXML
    private ListView<Quest> questsListView;
    @FXML
    void deleteChosenQuestOnMouseClicked(MouseEvent event) {
        Quest quest = questsListView.getSelectionModel().getSelectedItem();
        questDataAccess.deleteQuest(quest.getId());
        questsListView.getItems().remove(quest);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameLogListView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                String chosen = gameLogListView.getSelectionModel().getSelectedItem();
                gameLog.add("SOLVED: " + chosen);
                gameLogListView.getItems().remove(chosen);
                if (gameLogListView.getItems().isEmpty()) {
                    gameLog.add("GAME FINISHED");
                    initVBox();
                }

            }
        });

        pricePayment.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getPrice()));
        datePayment.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getStartTime()));
        roomPayment.setCellValueFactory(cellData -> Bindings.createObjectBinding(() ->cellData.getValue().getRoomId()));

        questsNewRoomListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        roomNameGames.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getRoomId()));
        scoreGames.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getScore()));
        startTimeGames.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getStartTime()));
        teamNameGames.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getTeamName()));

        scoreboardRoomChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            scoreboard();
        });
        teamScoreboardTableColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getTeamName()));
        scoreScoreboardTableColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getScore()));
        positionScoreboardTableColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getPosition()));
        completedScoreboardTableColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getFinished()));


        scoreboardTable.getSortOrder().add(positionScoreboardTableColumn);
        positionScoreboardTableColumn.setSortType(TableColumn.SortType.ASCENDING);

        scoreboardTable.sort();

        typeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            addPuzzlePane.setVisible(!newValue.equals("LOCK"));
        });

    }

    private void scoreboard() {
        scoreboardTable.getItems().clear();
        Room room = scoreboardRoomChoiceBox.getSelectionModel().getSelectedItem();
        List<Game> games = gameDataAccess.getGamesFromRoom(room.getName());
        List<GameWrapper> gameWrappers = Utils.gamesToWrapper(games);

        scoreboardTable.getItems().addAll(gameWrappers);
    }

    private void initVBox() {
        vboxReviews.getChildren().clear();
        List<HBox> reviews = new ArrayList<>();
        List<Player> players = generalDataAccess.getPlayersFromTeam(team.getName());

        reviewFields.clear();
        for (var player : players) {
            HBox hbox = createHBox(player.getName());
            reviews.add(hbox);
        }
        vboxReviews.getChildren().addAll(reviews);
        vboxReviews.setVisible(true);
    }

    private HBox createHBox(String player) {
        HBox hbox = new HBox();
        hbox.setPrefHeight(40.0);
        hbox.setPrefWidth(900.0);

        Label label = new Label(player);
        label.setPrefHeight(40.0);
        label.setPrefWidth(180.0);

        TextField textField = new TextField();
        textField.setPrefHeight(40.0);
        textField.setPrefWidth(720.0);

        hbox.getChildren().addAll(label, textField);

        reviewFields.put(player, textField);

        return hbox;
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

    public static void showStage() throws IOException {
        stage = new Stage();
        Utils.initStage(stage, "gamemaster_controller.fxml");
        stage.setMinHeight(1200);
        stage.setMinWidth(800);
    }

    // NOT IMPLEMENTED
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

}
