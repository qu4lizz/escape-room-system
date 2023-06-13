package qu4lizz.escape_room.model.system;

import qu4lizz.escape_room.model.economics.Reservation;
import qu4lizz.escape_room.model.game.Room;
import qu4lizz.escape_room.model.game.Team;
import qu4lizz.escape_room.model.users.Administrator;
import qu4lizz.escape_room.model.users.GameMaster;
import qu4lizz.escape_room.model.users.Player;
import qu4lizz.escape_room.model.users.User;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final System app = System.getInstance();
    public static void main(String[] args) {
        User user = login();

        if (user == null) {
            java.lang.System.out.println("Invalid username or password");
        }
        else if (user instanceof Administrator)
            adminInterface();
        else if (user instanceof GameMaster)
            gameMasterInterface();
        else
            throw new RuntimeException("User type not recognized");
    }

    public static User login() {
        Console console = java.lang.System.console();
        java.lang.System.out.println("Username: ");
        String username = console.readLine();
        java.lang.System.out.println("Password: ");
        char[] password = console.readPassword();

        return System.getInstance().checkUserCredentials(username, new String(password));
    }

    public static void adminInterface() {
        char option;
        Scanner scanner = new Scanner(java.lang.System.in);

        do {
            option = scanner.next().charAt(0);
            java.lang.System.out.println("1. Add user");
            java.lang.System.out.println("2. Remove user");
            java.lang.System.out.println("q. Quit");

            switch (option) {
                case '1' -> {
                    java.lang.System.out.println("Name: ");
                    String name = scanner.nextLine();
                    java.lang.System.out.println("Username: ");
                    String username = scanner.nextLine();
                    java.lang.System.out.println("Password: ");
                    String password = scanner.nextLine();
                    java.lang.System.out.println("Type (admin or gamemaster): ");
                    String type = scanner.nextLine();
                    if (type.equals("admin"))
                        app.addUser(new Administrator(name, username, password));
                    else if (type.equals("gamemaster"))
                        app.addUser(new GameMaster(name, username, password));
                    else
                        java.lang.System.out.println("Invalid type");
                }
                case '2' -> {
                    java.lang.System.out.println("Username: ");
                    String username = scanner.nextLine();
                    if (app.removeUser(username))
                        java.lang.System.out.println("User removed");
                    else
                        java.lang.System.out.println("User" + username + "not found");
                }
            }

            java.lang.System.out.println("=====================================");
        } while (option != 'q');
    }

    public static void gameMasterInterface() {
        char option;
        Scanner scanner = new Scanner(java.lang.System.in);

        do {
            option = scanner.next().charAt(0);
            java.lang.System.out.println("1. Add reservation");
            java.lang.System.out.println("2. Remove reservation");
            java.lang.System.out.println("3. Add room");
            java.lang.System.out.println("4. Modify room");
            java.lang.System.out.println("5. Remove room");
            java.lang.System.out.println("6. Start game");
            java.lang.System.out.println("q. Quit");

            switch (option) {
                case '1' -> { // Add reservation
                    boolean success = true;
                    java.lang.System.out.println("Choose room id: ");
                    for(Room room : app.getRooms())
                        java.lang.System.out.println(room.getName());
                    int roomId = scanner.nextInt();
                    Room room = app.getRoom(roomId);
                    if (room == null) {
                        java.lang.System.out.println("Room not found");
                        break;
                    }
                    char opt2;
                    java.lang.System.out.println("1. Add new team");
                    java.lang.System.out.println("2. Add existing team");
                    opt2 = scanner.next().charAt(0);
                    Team team = null;
                    switch (opt2) {
                        case '1' -> {
                            java.lang.System.out.println("Team name: ");
                            String teamName = scanner.nextLine();
                            java.lang.System.out.println("Team size: ");
                            int teamSize = scanner.nextInt();
                            Player[] players = new Player[teamSize];
                            for(int i = 0; i < teamSize; i++) {
                                java.lang.System.out.println("Player name: ");
                                String playerName = scanner.nextLine();
                                java.lang.System.out.println("Player email: ");
                                String playerEmail = scanner.nextLine();
                                players[i] = new Player(playerName, playerEmail);
                            }
                            team = new Team(teamName, players);
                        }
                        case '2' -> {
                            ArrayList<Team> teams = app.getTeams();
                            for (int i = 0; i < teams.size(); i++)
                                java.lang.System.out.println(i + ". " + teams.get(i).getName());
                            java.lang.System.out.println("Choose team id: ");
                            int teamId = scanner.nextInt();
                            try {
                                team = teams.get(teamId);
                            }
                            catch (ArrayIndexOutOfBoundsException e) {
                                java.lang.System.out.println("Team not found");
                                success = false;
                            }
                        }
                    }
                    String date = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
                    Date startDate = null;
                    try {
                        startDate = dateFormat.parse(date);
                    } catch (ParseException e) {
                        java.lang.System.out.println("Invalid date format");
                        success = false;
                    }
                    if (success)
                        app.addReservation(new Reservation(room.getId(), team.getName(), startDate));
                    else
                        java.lang.System.out.println("Reservation not added");
                }
                case '2' -> {

                }
            }

            java.lang.System.out.println("=====================================");
        } while (option != 'q');
    }
}
