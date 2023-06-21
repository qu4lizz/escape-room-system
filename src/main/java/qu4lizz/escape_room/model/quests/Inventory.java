package qu4lizz.escape_room.model.quests;

import java.util.HashSet;

public class Inventory {
    private int id;
    private String location;
    private HashSet<Quest> quests = new HashSet<>();

    public Inventory(String location) {
        this.location = location;
    }

    public Inventory(int id, String location) {
        this.location = location;
    }

    public Inventory(int id, String location, HashSet<Quest> quests) {
        this(location);
        this.id = id;
        this.quests = quests;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
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
}
