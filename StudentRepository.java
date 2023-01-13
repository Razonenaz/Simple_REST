import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepository implements IRepository<Student> {

    Connection conn = DBConnection.getConnection("postgres", "postgres", "1223334444");
    private String table = "";

    public StudentRepository(String table) {
        this.table = table;
    }

    public Student get(int id) {
        Statement statement = null;
        ResultSet rs = null;
        Student student = new Student();

        try {
            String sql = "SELECT * from " + table + " where std_id = " + id + ";";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            if (rs != null) {
                rs.next();

                student.setId(id);
                student.setName(rs.getString("std_name"));
                student.setCourse(rs.getString("std_course"));
                return student;
            }
        } catch (SQLException e) {
            DBConnection.loggerDB.error(e);
        }
        return null;
    }

    @Override
    public boolean add(Student student) {
        try {
            String sql = "INSERT INTO " + table + "(std_name, std_course) VALUES(?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql, new String[]{"std_id"});

            statement.setString(1, student.getName());
            statement.setString(2, student.getCourse());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            rs.next();
            DBConnection.loggerDB.info("Row has been inserted");

            return true;
        } catch (SQLException e) {
            DBConnection.loggerDB.error(e);
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        try {
            String sql = String.format("UPDATE %s SET std_name = '%s', std_course = '%s' WHERE std_id = %s;", table,
                    student.getName(), student.getCourse(), student.getId());
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            DBConnection.loggerDB.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {
        try {
            String sql = "DELETE FROM " + table + " where std_id =" + student.getId() + ";";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            DBConnection.loggerDB.error(e);
        }
        return false;
    }
}
