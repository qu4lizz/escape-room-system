package qu4lizz.escape_room.data.mysql;

import qu4lizz.escape_room.data.ReservationDataAccess;
import qu4lizz.escape_room.model.economics.Reservation;
import qu4lizz.escape_room.utils.ConnectionPool;
import qu4lizz.escape_room.utils.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDataAccessImpl implements ReservationDataAccess {
    @Override
    public boolean addReservation(Reservation reservation) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO Reservation (startDate, Room_name, Team_name) VALUES (?, ?, ?)";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setTimestamp(1, reservation.getStartTime());
            ps.setString(2, reservation.getRoom());
            ps.setString(3, reservation.getTeam());

            retVal = ps.executeUpdate() == 1;

            if (!retVal)
                SQLUtil.getInstance().showErrorMessage();
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
    public boolean updateReservation(Reservation reservation) {
        return false;
    }

    @Override
    public boolean deleteReservation(Reservation reservation) {
        boolean retVal = false;
        Connection connection = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM Reservation WHERE Room_name = ? AND startDate = ?";
        try {
            connection = ConnectionPool.getInstance().checkOut();
            ps = connection.prepareStatement(query);

            ps.setString(1, reservation.getRoom());
            ps.setTimestamp(2, reservation.getStartTime());

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            SQLUtil.getInstance().close(ps);
        }
        return retVal;
    }

    @Override
    public List<Reservation> getReservations() {
        List<Reservation> retVal = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Reservation";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next())
                retVal.add(new Reservation(rs.getString("Room_name"), rs.getString("Team_name"), rs.getTimestamp("startDate")));
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
    public List<Reservation> getActiveReservations() {
        List<Reservation> retVal = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT *\n" +
                "FROM Reservation R\n" +
                "LEFT OUTER JOIN Game G on R.Room_name = G.Room_name AND R.startDate = G.startDate\n" +
                "WHERE G.endDate IS NULL AND R.startDate > NOW() - INTERVAL 60 MINUTE";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next())
                retVal.add(new Reservation(rs.getString("Room_name"), rs.getString("Team_name"), rs.getTimestamp("startDate")));
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
