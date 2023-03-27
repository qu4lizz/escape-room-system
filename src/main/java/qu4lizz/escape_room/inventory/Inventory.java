package qu4lizz.escape_room.inventory;

import qu4lizz.escape_room.quests.Quest;

import java.util.HashSet;

public class Inventory {
    private HashSet<Quest> items = new HashSet<>();

    public void addItem(Quest item) {
        items.add(item);
    }

    public void removeItem(Quest item) {
        items.remove(item);
    }

    public HashSet<Quest> getItems() {
        return items;
    }
}
