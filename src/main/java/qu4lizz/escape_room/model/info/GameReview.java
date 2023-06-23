package qu4lizz.escape_room.model.info;

public class GameReview {
    private String player;
    private String review;


    public GameReview(String player, String review) {
        this.review = review;
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public String getReview() {
        return review;
    }
}
