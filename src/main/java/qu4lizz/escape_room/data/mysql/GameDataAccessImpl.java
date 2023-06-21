package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.data.GameDataAccess;
import qu4lizz.escape_room.model.game.Game;
import qu4lizz.escape_room.utils.ConnectionPool;
import qu4lizz.escape_room.utils.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDataAccessImpl implements GameDataAccess {
    @Override
    public boolean addGame(Game game) {
        boolean retVal = false;
        Connection conn = null;
        CallableStatement cs = null;

        String query = "{call addGame(?,?,?,?,?,?)}";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);
            cs.setString(1, game.getRoomId());
            cs.setTimestamp(2, game.getStartTime());
            cs.setTimestamp(3, game.getEndTime());
            cs.setLong(4, game.getScore());
            cs.setString(5, game.getTeamName());
            cs.setString(6, game.getGameMasterId());
            cs.setBigDecimal(7, game.getPrice());

            cs.execute();
            retVal = cs.executeUpdate() == 1;
            if (!retVal)
                SQLUtil.getInstance().showErrorMessage();

            query = "{call addReview(?,?,?,?)}";
            cs = conn.prepareCall(query);
            for (var rev : game.getGameReview()) {
                cs.setString(1, rev.getReview());
                cs.setString(2, rev.getPlayer());
                cs.setTimestamp(3, game.getStartTime());
                cs.setString(4, game.getRoomId());
                cs.execute();
            }
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
    public List<Game> getGames() {
        List<Game> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =    "SELECT * "
                        + "FROM Game";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new Game(rs.getString("Room_name"),
                                    rs.getTimestamp("startDate"),
                                    rs.getTimestamp("endDate"),
                                    rs.getLong("score"),
                                    rs.getString("Team_name"),
                                    rs.getString("GameMaster_User_username"),
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
    public List<Game> getGamesFromRoom(String roomName) {
        List<Game> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =    "SELECT * "
                        + "FROM Game "
                        + "WHERE Room_name = ?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, roomName);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new Game(rs.getString("Room_name"),
                                    rs.getTimestamp("startDate"),
                                    rs.getTimestamp("endDate"),
                                    rs.getLong("score"),
                                    rs.getString("Team_name"),
                                    rs.getString("GameMaster_User_username"),
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
}
