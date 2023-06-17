package qu4lizz.escape_room.model.game;

import qu4lizz.escape_room.model.quests.Quest;

import java.util.HashSet;

public class Room {
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

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public void removeQuest(Quest quest) {
        quests.remove(quest);
    }

    public HashSet<Quest> getQuests() {
        return quests;
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
        return room.name.equals(this.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
