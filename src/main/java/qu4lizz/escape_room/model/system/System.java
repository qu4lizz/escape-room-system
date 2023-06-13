package qu4lizz.escape_room.model.system;

import qu4lizz.escape_room.model.economics.Reservation;
import qu4lizz.escape_room.model.game.Game;
import qu4lizz.escape_room.model.game.Room;
import qu4lizz.escape_room.model.game.Team;
import qu4lizz.escape_room.model.info.GameReview;
import qu4lizz.escape_room.model.info.Scoreboard;
import qu4lizz.escape_room.model.users.GameMaster;
import qu4lizz.escape_room.model.users.User;

import java.util.ArrayList;

public class System {
    private static System instance;
    private System() { }
    public static System getInstance() {
        if (instance == null)
            instance = new System();
        return instance;
    }

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private ArrayList<Game> inProgressGames = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Game> getInProgressGames() {
        return inProgressGames;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public ArrayList<Team> getTeams() {
        ArrayList<Team> teams = new ArrayList<>();
        for (Game game : games) {
            Team team = findTeam(game.getTeam());
            if (!teams.contains(team))
                teams.add(team);
        }
        return teams;
    }

    public Team findTeam(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName))
                return team;
        }
        return null;
    }

    public Room findRoom(int roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId)
                return room;
        }
        return null;
    }

    public User checkUserCredentials(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    // Administrator methods
    public boolean addUser(User user) {
        if (users.contains(user))
            return false;
        else {
            users.add(user);
            return true;
        }
    }
    public boolean removeUser(String username) {
        if (users.contains(username)) {
            users.remove(username);
            return true;
        }
        else
            return false;
    }

    // GameMaster methods
    public boolean addGame(Game game) {
        if (games.contains(game))
            return false;
        else {
            games.add(game);
            return true;
        }
    }

    public boolean addRoom(Room room) {
        if (rooms.contains(room))
            return false;
        else {
            rooms.add(room);
            return true;
        }
    }
    public void modifyRoom(Room oldRoom, Room newRoom) {
        if (rooms.contains(oldRoom)) {
            rooms.remove(oldRoom);
            rooms.add(newRoom);
        }
    }
    public boolean removeRoom(Room room) {
        if (rooms.contains(room)) {
            rooms.remove(room);
            return true;
        }
        else
            return false;
    }

    public Scoreboard getScoreboard(Room room) {
        return room.getScoreboard();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public void startGame(Reservation reservation, GameMaster gameMaster) {
        Game game = new Game(reservation.getRoom().getId(), reservation.getTeam().getName(), gameMaster.getId());
        game.startGame();
        inProgressGames.add(game);
    }

    public void endGame(Game game) {
        game.endGame();
        inProgressGames.remove(game);
        games.add(game);
    }



    public void reviewGame(Game game, ArrayList<GameReview> reviews) {
        for (GameReview review : reviews)
            game.addReview(review);
    }


    public Room getRoom(int roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId)
                return room;
        }
        return null;
    }
}
