package qu4lizz.escape_room.model.quests;

abstract public class Task {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String solution;

    public Task(String name, String solution) {
        this.id = idCounter++;
        this.name = name;
        this.solution = solution;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSolution() {
        return solution;
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
        Task quest = (Task) obj;
        return quest.id == this.id;
    }
}
