import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseDAO {

    private static Logger loggerDB = LogManager.getLogger(DatabaseDAO.class.getName());

    public Connection connToDataBase(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                loggerDB.info("Connection Established");
            } else {
                loggerDB.info("Connection Failed");
            }
        } catch (Exception e) {
            loggerDB.error(e);
        }
        return conn;
    }

    public void insertRow(Connection conn, String tableName, Integer std_id, String std_name, String std_course) {
        Statement statement;
        try {
            String query = String.format("INSERT into %s values('%s','%s','%s');", tableName, std_id, std_name,
                    std_course);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            loggerDB.info("Row has been inserted");
        } catch (Exception e) {
            loggerDB.error(e);
        }
    }

    public void readData(Connection conn, String tableName) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * from %s", tableName);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                String infOut = String.format("'%s' '%s' '%s'", rs.getString("std_id"), rs.getString("std_name"),
                        rs.getString("std_course"));
                loggerDB.info(infOut);
            }
        } catch (Exception e) {
            loggerDB.error(e);
        }
    }

    public void updateName(Connection conn, String tableName, String new_name, String old_name) {
        Statement statement;
        try {
            String query = String.format("UPDATE %s set std_name = '%s' where std_name = '%s'", tableName, new_name,
                    old_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            loggerDB.info("Data has been updated");
        } catch (Exception e) {
            loggerDB.error(e);
        }
    }

    public void deleteRow(Connection conn, String tableName, String std_name) {
        Statement statement;
        try {
            String query = String.format("DELETE from %s where std_name = '%s'", tableName, std_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            loggerDB.info("Row has been deleted");
        } catch (Exception e) {
            loggerDB.error(e);
        }
    }
}
