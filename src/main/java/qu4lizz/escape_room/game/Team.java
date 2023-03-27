package qu4lizz.escape_room.game;

import qu4lizz.escape_room.users.Player;

public class Team {
    private String name;
    private Player[] players;

    public Team(String name, Player[] players) {
        this.players = players;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player[] getPlayers() {
        return players;
    }

    // when comparing new team, we care about the name and players
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
        Team team = (Team) obj;
        return team.name.equals(this.name) && team.players.equals(this.players);
    }

    // when comparing for scoreboard, we only care about the name
    public boolean equalsByName(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Team team = (Team) obj;
        return team.name.equals(this.name);
    }
}
