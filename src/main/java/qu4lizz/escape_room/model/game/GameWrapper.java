package qu4lizz.escape_room.model.game;

public class GameWrapper extends Game {
    private String finished;
    private Integer position;


    public GameWrapper(Game game, int position) {
        super(game.getRoomId(), game.getStartTime(), game.getEndTime(), game.getScore(), game.getTeamName(), game.getGameMasterId(), game.getPrice());
        this.finished = game.getScore() != Long.MAX_VALUE ? "Yes" : "No";
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public String getFinished() {
        return finished;
    }
}
