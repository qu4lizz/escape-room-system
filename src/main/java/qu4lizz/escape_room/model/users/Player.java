package qu4lizz.escape_room.model.users;

public class Player extends Person {
    public Player(String name, String email) {
        super(name, email);
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
        Player player = (Player) obj;
        return player.getEmail().equals(this.getEmail());
    }
}
