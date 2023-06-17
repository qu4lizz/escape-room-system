package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.data.RoomsDataAccess;
import qu4lizz.escape_room.model.game.Room;
import qu4lizz.escape_room.utils.ConnectionPool;
import qu4lizz.escape_room.utils.SQLUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomsDataAccessImpl implements RoomsDataAccess {
    @Override
    public boolean addRoom(Room room) {
        boolean retVal = false;
        Connection conn = null;
        CallableStatement cs = null;

        String query = "{call addRoom(?,?,?)}";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);

            cs.setString(1, room.getName());
            cs.setInt(2, room.getMaxPlayers());
            cs.setLong(3, room.getDuration());

            retVal = cs.executeUpdate() == 1;

            if (!retVal)
                SQLUtil.getInstance().showErrorMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLUtil.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            SQLUtil.getInstance().close(cs);
        }
        return retVal;
    }

    @Override
    public boolean updateRoom(String roomName) {
        return false;
    }

    @Override
    public boolean deleteRoom(String roomName) {
        boolean retVal = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String callStatementItem = "{call deleteQuest(?)}";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatementItem);
            callableStatement.setString(1, roomName);

            retVal = callableStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            SQLUtil.getInstance().close(callableStatement);
        }
        return retVal;
    }

    @Override
    public List<Room> getRooms() {
        return null;
    }
}
