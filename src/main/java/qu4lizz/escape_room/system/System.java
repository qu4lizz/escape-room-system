package qu4lizz.escape_room.system;

import qu4lizz.escape_room.game.Game;

import java.util.HashSet;

public class System {
    private static System instance;

    private HashSet<Game> games = new HashSet<>();

    private System() {
    }

    public static System getInstance() {
        if (instance == null) {
            instance = new System();
        }
        return instance;
    }

}
