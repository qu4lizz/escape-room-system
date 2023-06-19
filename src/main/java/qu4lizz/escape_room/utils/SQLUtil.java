package qu4lizz.escape_room.utils;

import qu4lizz.escape_room.controller.PopUpController;

import java.io.IOException;
import java.sql.*;

public class SQLUtil {
    private static SQLUtil instance;

    public static SQLUtil getInstance() {
        if (instance == null)
            instance = new SQLUtil();
        return instance;
    }

    private SQLUtil() { }


    public static String signIn(String username, String password) throws Exception {
        Connection conn = null;
        CallableStatement cs = null;
        boolean loginSuccessful;
        int userId;

        String callStatement = "{call prijava(?, ?, ?, ?)}";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(callStatement);
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.BOOLEAN);
            cs.registerOutParameter(4, Types.INTEGER);

            cs.executeUpdate();

            loginSuccessful = cs.getBoolean(3);
            userId = cs.getInt(4);

            return loginSuccessful + "#" + userId;

        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            SQLUtil.getInstance().close(cs);
        }
    }


    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(Statement s) {
        if (s != null) {
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(Connection conn, Statement s) {
        close(s);
        close(conn);
    }

    public void close(Connection conn, ResultSet rs) {
        close(rs);
        close(conn);
    }

    public void close(Statement s, ResultSet rs) {
        close(rs);
        close(s);
    }

    public void close(Connection conn, Statement s, ResultSet rs) {
        close(rs);
        close(s);
        close(conn);
    }

    public void showSQLException(SQLException e) {
        try {
            PopUpController.showStage("Error", "Code error: " + e.getErrorCode() + "\nMessage: " + e.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void showErrorMessage() {
        try {
            PopUpController.showStage("Error", "Something went wrong!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
