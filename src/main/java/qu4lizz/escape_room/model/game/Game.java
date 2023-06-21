package qu4lizz.escape_room.model.game;

import qu4lizz.escape_room.model.info.GameLog;
import qu4lizz.escape_room.model.info.GameReview;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;

public class Game {
    private String roomId;
    private String teamName;
    private String gameMasterUsername;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long score;
    private GameLog gameLog;
    private HashSet<GameReview> gameReview = new HashSet<>();
    private BigDecimal price;

    public Game(String roomId, String teamName, String gameMasterUsername) {
        this.roomId = roomId;
        this.teamName = teamName;
        this.gameMasterUsername = gameMasterUsername;
    }

    public Game(String roomId, Timestamp startTime, Timestamp endTime, Long score, String teamName, String gameMasterUsername, BigDecimal payment) {
        this.roomId = roomId;
        this.teamName = teamName;
        this.gameMasterUsername = gameMasterUsername;
        this.startTime = startTime;
        this.endTime = endTime;
        this.score = score;
        this.price = payment;
    }

    public long getScore() {
        return score;
    }

    public String getRoomId() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setGameLog(GameLog gameLog) {
        this.gameLog = gameLog;
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
