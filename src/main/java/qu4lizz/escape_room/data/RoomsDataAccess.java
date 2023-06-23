package qu4lizz.escape_room.data;

import qu4lizz.escape_room.model.game.Room;

import java.util.List;

public interface RoomsDataAccess {
    boolean addRoom(Room room);
    boolean updateRoom(String roomName);
    boolean deleteRoom(String roomName);
    List<Room> getRooms();

    Room getRoom(String roomName);
}
