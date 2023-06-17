package qu4lizz.escape_room.utils;

import qu4lizz.escape_room.controller.PopUpController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtil {
    private static SQLUtil instance;

    public static SQLUtil getInstance() {
        if (instance == null)
            instance = new SQLUtil();
        return instance;
    }

    private SQLUtil() { }



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
