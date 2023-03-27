package qu4lizz.escape_room.info;

public class GameReview {
    private String review;
    private Rating rating;

    public GameReview(String review, Rating rating) {
        this.review = review;
        this.rating = rating;
    }


    public enum Rating {
        ONE, TWO, THREE, FOUR, FIVE
    }
}
