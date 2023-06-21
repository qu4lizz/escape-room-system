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

        String query = "{call addQuest(?,?,?,?)}";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);

            cs.setString(1, quest.getName());
            cs.setString(2, quest.getSolution());
            cs.setInt(3, quest.getInventoryId());
            cs.registerOutParameter(4, Types.INTEGER);

            cs.executeUpdate();

            int questId = cs.getInt(4);
            PreparedStatement ps = null;
            if (quest instanceof Lock lock) {
                String queryLock = "INSERT INTO `Lock` (Quest_idQuest) VALUES (?);";
                ps = conn.prepareStatement(queryLock);
                ps.setInt(1, questId);
                retVal = ps.executeUpdate() == 1;
            } else if (quest instanceof Puzzle puzzle){
                String queryPuzzle = "INSERT INTO Puzzle (Quest_idQuest, difficulty) VALUES (?, ?);";
                ps = conn.prepareStatement(queryPuzzle);
                ps.setInt(1, questId);
                ps.setInt(2, puzzle.getDifficulty().ordinal());
                retVal = ps.executeUpdate() == 1;
            }
            else
                throw new RuntimeException("Unknown quest type");
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
    public boolean modifyQuest(int questId) {
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
        String queryLock = "SELECT * FROM Quest INNER JOIN `Lock` L ON Quest.idQuest = L.Quest_idQuest;";
        String queryPuzzle = "SELECT * FROM Quest INNER JOIN Puzzle ON Puzzle.Quest_idQuest = Quest.idQuest;";
        return refactor(queryLock, queryPuzzle);
    }

    @Override
    public List<Quest> getAvailableQuests() {
        String queryLock = "SELECT * FROM Quest INNER JOIN escape_room.Lock L ON L.Quest_idQuest=Quest.idQuest WHERE Room_name IS NULL;";
        String queryPuzzle = "SELECT * FROM Quest INNER JOIN Puzzle ON Puzzle.Quest_idQuest=Quest.idQuest WHERE Room_name IS NULL;";
        return refactor(queryLock, queryPuzzle);
    }

    private List<Quest> refactor(String queryLock, String queryPuzzle) {
        List<Quest> retVal = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryLock);

            while (rs.next()) {
                retVal.add(new Lock(rs.getInt("idQuest"),
                        rs.getString("name"),
                        rs.getString("solution"),
                        rs.getString("Room_name"),
                        rs.getInt("Inventory_idInventory")));
            }

            rs = stmt.executeQuery(queryPuzzle);

            while (rs.next()) {
                retVal.add(new Puzzle(rs.getInt("idQuest"),
                        rs.getString("name"),
                        rs.getString("solution"),
                        Difficulty.values()[rs.getInt("difficulty")],
                        rs.getString("Room_name"),
                        rs.getInt("Inventory_idInventory")));
            }
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
    public List<Quest> getQuestsForRoom(String roomName) {
        List<Quest> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String queryLock =    "SELECT * "
                            + "FROM Quest "
                            + "INNER JOIN `Lock` L ON Quest.idQuest = L.Quest_idQuest "
                            + "WHERE Room_name = ?;";

        String queryPuzzle =    "SELECT * "
                                + "FROM Quest "
                                + "INNER JOIN Puzzle ON Puzzle.Quest_idQuest=Quest.idQuest "
                                + "WHERE Room_name = ?;";

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

    @Override
    public boolean switchQuestPlace(Integer inventoryId, String roomName, int questId) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE Quest SET Inventory_idInventory = ?, Room_name = ? WHERE idQuest = ?;";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            if (inventoryId == null) {
                ps.setNull(1, Types.INTEGER);
                ps.setString(2, roomName);
            } else {
                ps.setInt(1, inventoryId);
                ps.setNull(2, Types.VARCHAR);
            }
            ps.setInt(3, questId);
            retVal = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            SQLUtil.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            SQLUtil.getInstance().close(ps);
        }
        return retVal;
    }
}
