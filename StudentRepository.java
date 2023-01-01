import java.sql.Connection;

public class StudentRepository {

    Connection conn = null;
    DBConnection serviceDB = new DBConnection();

    String tableName, std_name, std_course;
    Integer std_id;

    public StudentRepository() {
        conn = serviceDB.getConnection(conn, "postgres", "postgres", "1223334444");
    }

    void insertRow() {
        serviceDB.insertRow(conn, tableName, std_id, std_name, std_course);
    }

    void readData() {
        if (tableName != null) {
            serviceDB.readData(conn, tableName);
        }
    }

    void updateName() {
        serviceDB.updateName(conn, tableName, std_name, std_id);
    }

    void deleteRow() {
        serviceDB.deleteRow(conn, tableName, std_name);
    }
}
