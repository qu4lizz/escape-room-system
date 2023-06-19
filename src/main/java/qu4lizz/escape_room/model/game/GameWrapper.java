package qu4lizz.escape_room.model.game;

public class GameWrapper extends Game {
    private boolean finished;
    private Integer position;

    public GameWrapper(Game game, int position) {
        super(game.getRoomId(), game.getStartTime(), game.getEndTime(), game.getScore(), game.getTeamName(), game.getGameMasterId(), game.getPrice());
        this.finished = game.getScore() != Long.MAX_VALUE;
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public boolean isFinished() {
        return finished;
    }
}
