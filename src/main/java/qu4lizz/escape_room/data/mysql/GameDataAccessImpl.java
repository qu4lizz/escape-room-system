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
        } catch (SQLException e) {
            e.printStackTrace();
            SQLUtil.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            SQLUtil.getInstance().close(cs);
        }
        return retVal;
    }

    // TODO: FETCH PAYMENT, LOGS AND REVIEW
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
                retVal.add(new Game(rs.getString(1),
                                    rs.getTimestamp(2),
                                    rs.getTimestamp(3),
                                    rs.getLong(4),
                                    rs.getString(5),
                                    rs.getString(6),
                                    rs.getBigDecimal(7)));
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
