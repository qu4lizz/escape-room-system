package qu4lizz.escape_room.users;

public class Player extends Person {
    private String email;
    public Player(String name, String email) {
        super(name);
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
        return player.email.equals(this.email);
    }
}
