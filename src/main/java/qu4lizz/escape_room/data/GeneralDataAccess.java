package qu4lizz.escape_room.data;


import qu4lizz.escape_room.model.game.Team;
import qu4lizz.escape_room.model.users.Player;

import java.util.List;

public interface GeneralDataAccess {
    List<Player> getPlayersFromTeam(String team);

    List<Team> getTeams();
}
