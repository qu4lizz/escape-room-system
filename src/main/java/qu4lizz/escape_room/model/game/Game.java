package qu4lizz.escape_room.model.game;

import qu4lizz.escape_room.model.info.GameLog;
import qu4lizz.escape_room.model.info.GameReview;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;

public class Game {
    private int roomId;
    private String teamName;
    private String gameMasterUsername;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long score;
    private GameLog gameLog = new GameLog();
    private HashSet<GameReview> gameReview = new HashSet<>();
    private BigDecimal payment;

    public Game(int roomId, String teamName, String gameMasterUsername) {
        this.roomId = roomId;
        this.teamName = teamName;
        this.gameMasterUsername = gameMasterUsername;
    }

    public Game(int roomId, Timestamp startTime, Timestamp endTime, Long score, String teamName, String gameMasterUsername, BigDecimal payment) {
        this.roomId = roomId;
        this.teamName = teamName;
        this.gameMasterUsername = gameMasterUsername;
        this.startTime = startTime;
        this.endTime = endTime;
        this.score = score;
    }

    public long getScore() {
        return score;
    }

    public String getTeam() {
        return teamName;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getGameMasterId() {
        return gameMasterUsername;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public GameLog getGameLog() {
        return gameLog;
    }

    public HashSet<GameReview> getGameReview() {
        return gameReview;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void startGame() {
        this.startTime = new Timestamp(System.currentTimeMillis());
    }
    public void endGame() {
        this.endTime = new Timestamp(System.currentTimeMillis());
        this.score = this.endTime.getTime() - this.startTime.getTime();
        // add game to the scoreboard
    }

    public void timedOut() {
        this.endTime = new Timestamp(System.currentTimeMillis());
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
