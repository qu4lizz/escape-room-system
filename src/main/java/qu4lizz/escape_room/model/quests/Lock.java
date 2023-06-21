package qu4lizz.escape_room.model.quests;

public class Lock extends Quest {

    public Lock(String name, String solution, String roomName, Integer inventoryId) {
        super(name, solution, roomName, inventoryId);
    }

    public Lock(int id, String name, String solution, String roomName, Integer inventoryId) {
        super(id, name, solution, roomName, inventoryId);
    }

}
