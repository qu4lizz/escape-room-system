package qu4lizz.escape_room.data;

import qu4lizz.escape_room.model.game.Game;

import java.util.List;

public interface GameDataAccess {
    boolean addGame(Game game);
    List<Game> getGames();

    List<Game> getGamesFromRoom(String roomName);
}
