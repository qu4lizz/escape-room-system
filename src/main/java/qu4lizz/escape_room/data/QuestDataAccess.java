package qu4lizz.escape_room.data;

import qu4lizz.escape_room.model.quests.Quest;

import java.util.List;

public interface QuestDataAccess {
    boolean addQuest(Quest quest);
    List<Quest> getQuests();
    boolean modifyQuest(int questId);
    boolean deleteQuest(int questId);

    List<Quest> getAvailableQuests();

    List<Quest> getQuestsForRoom(String roomName);

    boolean switchQuestPlace(Integer inventoryId, String roomName, int questId);
}
