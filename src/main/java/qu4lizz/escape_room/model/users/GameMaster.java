package qu4lizz.escape_room.model.users;

public class GameMaster extends User {
    private static int idCounter = 0;
    private int id;
    public GameMaster(String name, String username, String password) {
        super(name, username, password);
        this.id = id++;
    }

    public int getId() {
        return id;
    }
}
