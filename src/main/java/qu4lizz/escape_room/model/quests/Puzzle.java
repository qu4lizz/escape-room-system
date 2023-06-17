package qu4lizz.escape_room.model.quests;

public class Puzzle extends Quest {
    private Difficulty difficulty;

    public Puzzle(String name, String solution, Difficulty difficulty) {
        super(name, solution);
        this.difficulty = difficulty;
    }

    public Puzzle(int id, String name, String solution, Difficulty difficulty) {
        super(id, name, solution);
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
