package qu4lizz.escape_room.model.game;

import qu4lizz.escape_room.model.economics.Payment;
import qu4lizz.escape_room.model.info.GameLog;
import qu4lizz.escape_room.model.info.GameReview;

import java.util.Date;
import java.util.HashSet;

public class Game {
    private int roomId;
    private String teamName;
    private int gameMasterId;
    private Date startTime;
    private Date endTime;
    private Long score;
    private GameLog gameLog = new GameLog();
    private HashSet<GameReview> gameReview = new HashSet<>();
    private Payment payment;

    public Game(int roomId, String teamName, int gameMasterId) {
        this.roomId = roomId;
        this.teamName = teamName;
        this.gameMasterId = gameMasterId;
    }

    public long getScore() {
        return score;
    }

    public String getTeam() {
        return teamName;
    }

    public void startGame() {
        this.startTime = new Date();
    }
    public void endGame() {
        this.endTime = new Date();
        this.score = this.endTime.getTime() - this.startTime.getTime();
        // add game to the scoreboard
    }

    public void timedOut() {
        this.endTime = new Date();
        this.score = Long.MAX_VALUE;
    }

    public void addReview(GameReview review) {
        gameReview.add(review);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Game game = (Game) obj;
        return game.roomId == roomId && game.startTime.equals(this.startTime);
    }
}
