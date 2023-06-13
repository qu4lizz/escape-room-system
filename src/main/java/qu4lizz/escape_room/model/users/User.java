package qu4lizz.escape_room.model.users;

public abstract class User extends Person {
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
        User user = (User) obj;
        return user.username.equals(this.username);
    }
}
