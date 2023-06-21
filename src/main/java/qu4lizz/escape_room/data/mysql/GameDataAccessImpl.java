package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.controller.SignInController;
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
        PreparedStatement ps = null;

        String query = "INSERT INTO Game (Room_name, startDate, endDate, score, Team_name, GameMaster_User_username, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, game.getRoomId());
            ps.setTimestamp(2, game.getStartTime());
            ps.setTimestamp(3, game.getEndTime());
            ps.setLong(4, game.getScore());
            ps.setString(5, game.getTeamName());
            ps.setString(6, SignInController.username);
            ps.setBigDecimal(7, game.getPrice());

            retVal = ps.executeUpdate() == 1;
            if (!retVal)
                SQLUtil.getInstance().showErrorMessage();

            query = "INSERT INTO GameReview (review, Player_NonUser_email, Game_startDate, Game_Room_name) VALUES (?, ?, ?, ?)";
            for (var rev : game.getGameReview()) {
                ps = conn.prepareStatement(query);
                ps.setString(1, rev.getReview());
                ps.setString(2, rev.getPlayer());
                ps.setTimestamp(3, game.getStartTime());
                ps.setString(4, game.getRoomId());
                ps.execute();
            }

            // game log query
            query = "INSERT INTO GameLog (log, Game_startDate, Game_Room_name) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, game.getGameLog().getLog());
            ps.setTimestamp(2, game.getStartTime());
            ps.setString(3, game.getRoomId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLUtil.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            SQLUtil.getInstance().close(ps);
        }
        return retVal;
    }

    @Override
    public List<Game> getGames() {
        List<Game> retVal = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Game";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

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
            SQLUtil.getInstance().close(stmt, rs);
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
