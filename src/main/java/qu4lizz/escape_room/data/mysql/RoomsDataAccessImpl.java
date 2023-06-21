package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.data.RoomsDataAccess;
import qu4lizz.escape_room.model.game.Room;
import qu4lizz.escape_room.utils.ConnectionPool;
import qu4lizz.escape_room.utils.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomsDataAccessImpl implements RoomsDataAccess {
    @Override
    public boolean addRoom(Room room) {
        boolean retVal = false;
        Connection conn = null;
        CallableStatement cs = null;

        String query = "{call addRoom(?,?,?,?)}";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);

            cs.setString(1, room.getName());
            cs.setInt(2, room.getMaxPlayers());
            cs.setTime(3, room.getDuration());
            cs.setBigDecimal(4, room.getPrice());

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
        String callStatementItem = "{call deleteRoom(?)}";

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
        List<Room> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =    "SELECT * "
                        + "FROM Room";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new Room(rs.getString("name"),
                                    rs.getInt("maxPlayers"),
                                    rs.getTime("duration"),
                                    rs.getBigDecimal("price")));
        } catch (SQLException e) {
            e.printStackTrace();
            SQLUtil.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            SQLUtil.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public Room getRoom(String roomName) {
        Room retVal = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =    "SELECT * "
                        + "FROM Room"
                        + "WHERE name='" + roomName + "';";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            retVal =   new Room(rs.getString("name"),
                                rs.getInt("maxPlayers"),
                                rs.getTime("duration"),
                                rs.getBigDecimal("price"));
        } catch (SQLException e) {
            e.printStackTrace();
            SQLUtil.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            SQLUtil.getInstance().close(ps, rs);
        }
        return retVal;
    }
}
