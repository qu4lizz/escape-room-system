package qu4lizz.escape_room.model.economics;

import java.sql.Timestamp;

public class Reservation {
    private String roomId;
    private String teamName;
    private Timestamp startTime;

    public Reservation(String room, String team, Timestamp startTime) {
        this.roomId = room;
        this.teamName = team;
        this.startTime = startTime;
    }



    public String getRoom() {
        return roomId;
    }

    public String getTeam() {
        return teamName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "roomId='" + roomId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
