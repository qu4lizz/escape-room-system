package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.data.QuestDataAccess;
import qu4lizz.escape_room.model.quests.Puzzle;
import qu4lizz.escape_room.model.quests.*;
import qu4lizz.escape_room.utils.ConnectionPool;
import qu4lizz.escape_room.utils.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestDataAccessImpl implements QuestDataAccess {
    @Override
    public boolean addQuest(Quest quest) {
        boolean retVal = false;
        Connection conn = null;
        CallableStatement cs = null;

        String query = "{call addQuest(?,?)}";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);

            cs.setString(1, quest.getName());
            cs.setString(2, quest.getSolution());
            cs.registerOutParameter(3, Types.INTEGER);

            cs.executeUpdate();

            int questId = cs.getInt(3);

            if (quest instanceof Lock) {
                Lock lock = (Lock) quest;
                String csLock = "{call addLock(?)}";
                cs = conn.prepareCall(csLock);
                cs.setInt(1, questId);
                retVal = cs.executeUpdate() == 1;
            } else if (quest instanceof Puzzle){
                Puzzle puzzle = (Puzzle) quest;
                String csPuzzle = "{call addPuzzle(?,?)}";
                cs = conn.prepareCall(csPuzzle);
                cs.setInt(1, questId);
                cs.setInt(2, puzzle.getDifficulty().ordinal());
                retVal = cs.executeUpdate() == 1;
            }
            else
                throw new RuntimeException("Unknown quest type");

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

    @Override
    public boolean updateQuest(int questId) {
        return false;
    }

    @Override
    public boolean deleteQuest(int questId) {
        boolean retVal = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String callStatementItem = "{call deleteQuest(?)}";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatementItem);
            callableStatement.setInt(1, questId);
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
    public List<Quest> getQuests() {
        List<Quest> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String queryLock =    "SELECT * "
                            + "FROM Quest"
                            + "INNER JOIN `Lock` L ON Quest.idQuest = L.Quest_idQuest;";

        String queryPuzzle =    "SELECT * "
                                + "FROM Quest "
                                + "INNER JOIN Puzzle ON Puzzle.Quest_idQuest=Quest.idQuest;";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(queryLock);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new Lock(rs.getInt("idQuest"),
                        rs.getString("name"),
                        rs.getString("solution"),
                        rs.getString("Room_name"),
                        rs.getInt("Inventory_idInventory")));

            ps = conn.prepareStatement(queryPuzzle);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new Puzzle(rs.getInt("idQuest"),
                        rs.getString("name"),
                        rs.getString("solution"),
                        Difficulty.values()[rs.getInt("difficulty")],
                        rs.getString("Room_name"),
                        rs.getInt("Inventory_idInventory")));
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
    public List<Quest> getQuestsForRoom(String roomName) {
        List<Quest> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String queryLock =    "SELECT * "
                            + "FROM Quest "
                            + "INNER JOIN `Lock` L ON Quest.idQuest = L.Quest_idQuest "
                            + "WHERE L.Room_name = ?;";

        String queryPuzzle =    "SELECT * "
                                + "FROM Quest "
                                + "INNER JOIN Puzzle ON Puzzle.Quest_idQuest=Quest.idQuest "
                                + "WHERE Puzzle.Room_name = ?;";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(queryLock);
            ps.setString(1, roomName);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new Lock(rs.getInt("idQuest"),
                        rs.getString("name"),
                        rs.getString("solution"),
                        rs.getString("Room_name"),
                        rs.getInt("Inventory_idInventory")));

            ps = conn.prepareStatement(queryPuzzle);
            ps.setString(1, roomName);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new Puzzle(rs.getInt("idQuest"),
                        rs.getString("name"),
                        rs.getString("solution"),
                        Difficulty.values()[rs.getInt("difficulty")],
                        rs.getString("Room_name"),
                        rs.getInt("Inventory_idInventory")));
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
