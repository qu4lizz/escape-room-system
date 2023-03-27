package qu4lizz.escape_room.quests;

abstract public class Quest {
    private String id;
    private String name;
    private String solution;

    public Quest(String id, String name, String solution) {
        this.id = id;
        this.name = name;
        this.solution = solution;
    }

    public String getId() {
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
        return quest.id.equals(this.id);
    }
}
