package qu4lizz.escape_room.model.game;

import qu4lizz.escape_room.model.quests.Quest;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.HashSet;

public class Room {
    private String name;
    private int maxPlayers;
    private Time duration;
    private BigDecimal price;
    private HashSet<Quest> quests = new HashSet<>();

    public Room(String name, int maxPlayers, Time duration, BigDecimal price) {
        this.maxPlayers = maxPlayers;
        this.duration = duration;
        this.name = name;
        this.price = price;
    }
    public Room(String name, int maxPlayers, Time duration, BigDecimal price, HashSet<Quest> quests) {
        this(name, maxPlayers, duration, price);
        this.quests = quests;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public Time getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
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
