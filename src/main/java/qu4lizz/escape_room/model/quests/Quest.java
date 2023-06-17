package qu4lizz.escape_room.model.quests;

abstract public class Quest {
    private int id;
    private String name;
    private String solution;

    public Quest(String name, String solution) {
        this.name = name;
        this.solution = solution;
    }

    public Quest(int id, String name, String solution) {
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
        Quest quest = (Quest) obj;
        return quest.id == this.id;
    }
}
