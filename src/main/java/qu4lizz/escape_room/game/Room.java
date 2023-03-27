package qu4lizz.escape_room.game;

import qu4lizz.escape_room.quests.Quest;

import java.util.HashSet;

public class Room {
    private int id;
    private String name;
    private int maxPlayers;
    private long duration;
    private HashSet<Quest> quests = new HashSet<>();

    public Room(String name, int maxPlayers, long duration) {
        this.maxPlayers = maxPlayers;
        this.duration = duration;
        this.name = name;
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

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public HashSet<Quest> getQuests() {
        return quests;
    }
}
