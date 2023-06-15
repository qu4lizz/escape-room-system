package qu4lizz.escape_room.data;

import qu4lizz.escape_room.model.quests.Task;

import java.util.List;

public interface QuestDataAccess {
    boolean addQuest(Task quest);
    List<Task> getQuests();
    boolean updateQuest(int questId);
    boolean deleteQuest(int questId);
}
