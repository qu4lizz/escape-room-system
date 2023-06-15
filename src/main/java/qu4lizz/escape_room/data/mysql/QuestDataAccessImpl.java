package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.data.QuestDataAccess;
import qu4lizz.escape_room.model.quests.Task;

import java.util.List;

public class QuestDataAccessImpl implements QuestDataAccess {
    @Override
    public boolean addQuest(Task quest) {
        return false;
    }

    @Override
    public boolean updateQuest(int questId) {
        return false;
    }

    @Override
    public boolean deleteQuest(int questId) {
        return false;
    }

    @Override
    public List<Task> getQuests() {
        return null;
    }
}
