package qu4lizz.escape_room.model.info;

public class GameLog {
    private StringBuilder log = new StringBuilder();

    public void add(String message) {
        log.append(message).append("\n");
    }

    public String getLog() {
        return log.toString();
    }
}
