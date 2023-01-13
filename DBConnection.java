import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection {

    public static Logger loggerDB = LogManager.getLogger(DBConnection.class.getName());

    private static Connection conn;
    public static Connection getConnection(String dbName, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, password);

            loggerDB.info("Connection Established");

        } catch (Exception e) {
            loggerDB.error(e);
        }
        return conn;
    }
}