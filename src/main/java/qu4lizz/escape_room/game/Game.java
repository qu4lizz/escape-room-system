package qu4lizz.escape_room.game;
import qu4lizz.escape_room.info.GameLog;
import qu4lizz.escape_room.info.GameReview;
import qu4lizz.escape_room.users.GameMaster;

import java.util.Date;
import java.util.HashMap;

public class Game {
    private String name;
    private Room room;
    private Team team;
    private GameMaster gameMaster;
    private Date startTime;
    private Date endTime;
    private long score;
    private GameLog gameLog = new GameLog();
    private HashMap<String, GameReview> gameReview = new HashMap<>();

    public Game(String name, Room room, Team team, GameMaster gameMaster) {
        this.name = name;
        this.room = room;
        this.team = team;
        this.gameMaster = gameMaster;
    }

    public long getScore() {
        return score;
    }

    public void startGame() {
        this.startTime = new Date();
    }
    public void endGame() {
        this.endTime = new Date();
        this.score = this.endTime.getTime() - this.startTime.getTime();
        // add game to scoreboard
    }

    public void review(String author, String review, GameReview.Rating rating) {
        gameReview.put(author, new GameReview(review, rating));
    }
}
