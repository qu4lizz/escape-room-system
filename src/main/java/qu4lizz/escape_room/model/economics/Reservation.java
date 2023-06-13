package qu4lizz.escape_room.model.economics;

import qu4lizz.escape_room.model.game.Room;
import qu4lizz.escape_room.model.game.Team;
import qu4lizz.escape_room.model.system.System;

import java.util.Date;

public class Reservation {
    private int roomId;
    private String teamName;
    private Date startTime;

    public Reservation(int room, String team, Date startTime) {
        this.roomId = room;
        this.teamName = team;
        this.startTime = startTime;
    }

    public Room getRoom() {
        return System.getInstance().findRoom(roomId);
    }

    public Team getTeam() {
        return System.getInstance().findTeam(teamName);
    }

    public Date getStartTime() {
        return startTime;
    }
}
