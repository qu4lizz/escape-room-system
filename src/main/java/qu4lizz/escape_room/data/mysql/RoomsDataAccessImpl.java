package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.data.RoomsDataAccess;
import qu4lizz.escape_room.model.game.Room;

import java.util.List;

public class RoomsDataAccessImpl implements RoomsDataAccess {
    @Override
    public boolean addRoom(Room room) {
        return false;
    }

    @Override
    public boolean updateRoom(String roomName) {
        return false;
    }

    @Override
    public boolean deleteRoom(String roomName) {
        return false;
    }

    @Override
    public List<Room> getRooms() {
        return null;
    }
}
