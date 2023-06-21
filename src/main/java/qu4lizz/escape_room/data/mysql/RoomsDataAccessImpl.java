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
        PreparedStatement stmt = null;

        String query = "INSERT INTO Room (name, maxPlayers, duration, price) VALUES (?, ?, ?, ?)";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            stmt = conn.prepareStatement(query);

            stmt.setString(1, room.getName());
            stmt.setInt(2, room.getMaxPlayers());
            stmt.setTime(3, room.getDuration());
            stmt.setBigDecimal(4, room.getPrice());

            retVal = stmt.executeUpdate() == 1;

            if (!retVal)
                SQLUtil.getInstance().showErrorMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLUtil.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            SQLUtil.getInstance().close(stmt);
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
        PreparedStatement ps = null;
        String query = "DELETE FROM Room WHERE name = ?";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            ps = connection.prepareStatement(query);
            ps.setString(1, roomName);
            retVal = ps.executeUpdate() == 1;
            if (!retVal)
                SQLUtil.getInstance().showErrorMessage();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            SQLUtil.getInstance().close(ps);
        }
        return retVal;
    }

    @Override
    public List<Room> getRooms() {
        List<Room> retVal = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Room";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

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
            SQLUtil.getInstance().close(stmt, rs);
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
                        + "FROM Room "
                        + "WHERE name = ?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, roomName);
            rs = ps.executeQuery();

            if (rs.next())
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
