package qu4lizz.escape_room.model.quests;

public class Puzzle extends Task {
    private Difficulty difficulty;

    public Puzzle(String name, Difficulty difficulty, String solution) {
        super(name, solution);
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
