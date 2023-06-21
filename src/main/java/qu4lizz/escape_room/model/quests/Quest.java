package qu4lizz.escape_room.model.quests;

abstract public class Quest {
    private int id;
    private String name;
    private String solution;
    private String roomName;
    private Integer inventoryId;

    public Quest(String name, String solution, String roomName, Integer inventoryId) {
        this.name = name;
        this.solution = solution;
        this.roomName = roomName;
        this.inventoryId = inventoryId;
    }

    public Quest(int id, String name, String solution, String roomName, Integer inventoryId) {
        this(name, solution, roomName, inventoryId);
        this.id = id;
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

    public String getRoomName() {
        return roomName;
    }

    public Integer getInventoryId() {
        return inventoryId;
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

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
