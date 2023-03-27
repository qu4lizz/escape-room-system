package qu4lizz.escape_room.quests;

public class Puzzle extends Quest {
    private Difficulty difficulty;

    public Puzzle(String name, String id, Difficulty difficulty, String solution) {
        super(name, id, solution);
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
