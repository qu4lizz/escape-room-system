package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.data.GeneralDataAccess;
import qu4lizz.escape_room.model.game.Team;
import qu4lizz.escape_room.model.quests.Inventory;
import qu4lizz.escape_room.model.users.Player;
import qu4lizz.escape_room.utils.ConnectionPool;
import qu4lizz.escape_room.utils.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GeneralDataAccessImpl implements GeneralDataAccess {
    @Override
    public List<Player> getPlayersFromTeam(String team) {
        List<Player> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =    "SELECT NU.email, NU.name, Player_has_Team.Team_name "
                        + "FROM Player_has_Team "
                        + "INNER JOIN NonUser NU on Player_has_Team.Player_NonUser_email = NU.email "
                        + "WHERE Team_name=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, team);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new Player(rs.getString(2), rs.getString(1)));
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
    public List<Team> getTeams() {
        List<Team> retVal = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Team";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String teamName = rs.getString("name");
                List<Player> players = getPlayersFromTeam(teamName);
                retVal.add(new Team(teamName, players.toArray(new Player[0])));
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
    public Team getTeamByName(String name) {
        Team retVal = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =    "SELECT * "
                        + "FROM Team "
                        + "WHERE name = ?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
                String teamName = rs.getString("name");
                List<Player> players = getPlayersFromTeam(teamName);
                retVal = new Team(teamName, players.toArray(new Player[0]));
            }
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
    public List<Inventory> getInventories() {
        List<Inventory> retVal = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Inventory";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                retVal.add(new Inventory(rs.getInt("idInventory"), rs.getString("location")));
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
}
