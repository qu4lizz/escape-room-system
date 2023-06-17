package qu4lizz.escape_room.model.economics;

import qu4lizz.escape_room.model.game.Room;
import qu4lizz.escape_room.model.game.Team;
import qu4lizz.escape_room.model.system.System;

import java.sql.Timestamp;

public class Reservation {
    private int roomId;
    private String teamName;
    private Timestamp startTime;

    public Reservation(int room, String team, Timestamp startTime) {
        this.roomId = room;
        this.teamName = team;
        this.startTime = startTime;
    }



    public int getRoom() {
        return roomId;
    }

    public String getTeam() {
        return teamName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }
}
