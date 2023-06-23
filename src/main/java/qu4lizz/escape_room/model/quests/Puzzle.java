package qu4lizz.escape_room.model.quests;

public class Puzzle extends Quest {
    private Difficulty difficulty;

    public Puzzle(String name, String solution, Difficulty difficulty, String roomName, Integer inventoryId) {
        super(name, solution, roomName, inventoryId);
        this.difficulty = difficulty;
    }

    public Puzzle(int id, String name, String solution, Difficulty difficulty, String roomName, Integer inventoryId) {
        super(id, name, solution, roomName, inventoryId);
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
