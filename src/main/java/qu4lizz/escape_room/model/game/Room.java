package qu4lizz.escape_room.model.game;

import qu4lizz.escape_room.model.info.Scoreboard;
import qu4lizz.escape_room.model.quests.Task;

import java.util.HashSet;

public class Room {
    private static int idCounter = 0;
    private final int id;
    private String name;
    private int maxPlayers;
    private long duration;
    private HashSet<Task> quests = new HashSet<>();
    private Scoreboard scoreboard;

    public Room(String name, int maxPlayers, long duration) {
        this.id = idCounter++;
        this.maxPlayers = maxPlayers;
        this.duration = duration;
        this.name = name;
        this.scoreboard = new Scoreboard(id);
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public long getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addQuest(Task quest) {
        quests.add(quest);
    }

    public void removeQuest(Task quest) {
        quests.remove(quest);
    }

    public HashSet<Task> getQuests() {
        return quests;
    }

    public void addScore(Game game) {
        scoreboard.addGame(game);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
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
        Room room = (Room) obj;
        return room.id == this.id;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
