package qu4lizz.escape_room.model.info;

import qu4lizz.escape_room.model.game.Game;
import qu4lizz.escape_room.model.game.Room;
import qu4lizz.escape_room.model.system.System;

import java.util.PriorityQueue;

public class Scoreboard {
    private int roomId;
    private PriorityQueue<Game> games = new PriorityQueue<>((x, y) -> (int) (y.getScore() - x.getScore()));

    public Scoreboard(int roomId) {
        Room room = System.getInstance().findRoom(roomId);
        if (room == null)
            throw new IllegalArgumentException("Room with id " + roomId + " does not exist");
        this.roomId = roomId;
    }
    public PriorityQueue<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Room room = System.getInstance().findRoom(roomId);
        sb.append("Scoreboard for room ").append(room.getName()).append(":\n");
        for (Game game : games) {
            sb.append(game.getTeam()).append(" - ");
            if (game.getScore() != Long.MAX_VALUE)
                sb.append(game.getScore());
            else
                sb.append("Timed out\n");
        }
        return sb.toString();
    }
}
