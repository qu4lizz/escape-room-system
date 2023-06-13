package qu4lizz.escape_room.model.info;

public class GameReview {
    private String player;
    private String review;
    private Rating rating;

    public GameReview(String player, String review, Rating rating) {
        this.review = review;
        this.rating = rating;
        this.player = player;
    }


    public enum Rating {
        ONE, TWO, THREE, FOUR, FIVE
    }
}
