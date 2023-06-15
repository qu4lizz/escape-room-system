package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.data.GameDataAccess;
import qu4lizz.escape_room.model.game.Game;

import java.util.List;

public class GameDataAccessImpl implements GameDataAccess {
    @Override
    public boolean addGame(Game game) {
        return false;
    }

    @Override
    public List<Game> getGames() {
        return null;
    }
}
