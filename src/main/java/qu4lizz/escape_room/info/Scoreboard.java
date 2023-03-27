package qu4lizz.escape_room.info;

import qu4lizz.escape_room.game.Game;
import qu4lizz.escape_room.game.Room;

import java.util.PriorityQueue;

public class Scoreboard {
    private Room room;
    private PriorityQueue<Game> games = new PriorityQueue<>((x, y) -> (int) (y.getScore() - x.getScore()));

    public PriorityQueue<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

}
