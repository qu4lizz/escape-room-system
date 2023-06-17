package qu4lizz.escape_room.data;

import qu4lizz.escape_room.model.quests.Quest;

import java.util.List;

public interface QuestDataAccess {
    boolean addQuest(Quest quest);
    List<Quest> getQuests();
    boolean updateQuest(int questId);
    boolean deleteQuest(int questId);
}
