package qu4lizz.escape_room.users;

public class User extends Person {
    private String username;
    private String password;

    public User(String name, String username, String password) {
        super(name);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
