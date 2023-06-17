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
        CallableStatement cs = null;

        String query = "{call addReservation(?,?,?)}";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);

            cs.setInt(1, reservation.getRoom());
            cs.setString(2, reservation.getTeam());
            cs.setTimestamp(3, reservation.getStartTime());

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
    public boolean updateReservation(Reservation reservation) {
        return false;
    }

    @Override
    public boolean deleteReservation(Reservation reservation) {
        boolean retVal = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String callStatementItem = "{call deleteQuest(?,?)}";

        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall(callStatementItem);
            callableStatement.setInt(1, reservation.getRoom());
            callableStatement.setTimestamp(2, reservation.getStartTime());
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
    public List<Reservation> getReservations() {
        List<Reservation> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =    "SELECT * "
                        + "FROM Reservation";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new Reservation(rs.getInt(1), rs.getString(2), rs.getTimestamp(3)));
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
