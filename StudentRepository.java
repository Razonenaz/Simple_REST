import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentRepository implements IRepository<Student> {

    private Connection conn = DBConnection.getConnection("postgres", "postgres", "1223334444");
    private String table = "";

    public StudentRepository(String table) {
        this.table = table;
    }

    @Override
    public Student get(int id) throws SQLException {
        Student student;
        Statement statement = null;
        ResultSet rs = null;
        String sql = String.format("SELECT * FROM %s WHERE std_id = %s ;", table, id);

        statement = conn.createStatement();
        rs = statement.executeQuery(sql);

        if (rs != null) {
            rs.next();
            String firstName = rs.getString("first_name");
            String middleName = rs.getString("middle_name");
            String lastName = rs.getString("last_name");

            student = new Student(id, firstName, middleName, lastName);

            return student;
        }

        return null;
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException {
        ArrayList<Student> list = new ArrayList<Student>();
        Student student;
        Statement statement = null;
        ResultSet rs = null;
        String sql = String.format("SELECT * FROM %s;", table);

        statement = conn.createStatement();
        rs = statement.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("std_id");
            String firstName = rs.getString("first_name");
            String middleName = rs.getString("middle_name");
            String lastName = rs.getString("last_name");

            student = new Student(id, firstName, middleName, lastName);
            list.add(student);
        }

        return list;
    }

    @Override
    public Student add(Student student) throws SQLException {
        String sql = String.format("INSERT INTO %s (first_name, middle_name, last_name) VALUES ('%s', '%s', '%s');",
                table, student.getFirstName(), student.getMiddleName(), student.getLastName());

        PreparedStatement statement = conn.prepareStatement(sql, new String[]{"std_id"});
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();

        DBConnection.loggerDB.info("Row has been inserted");

        return student;
    }

    @Override
    public Student update(Student student) throws SQLException {
        String sql = String.format(
                "UPDATE %s SET first_name = '%s', middle_name = '%s', last_name = '%s' WHERE std_id = %s;", table,
                student.getFirstName(), student.getMiddleName(), student.getLastName(), student.getId());

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.executeUpdate();

        DBConnection.loggerDB.info("Row has been updated");

        return student;
    }

    @Override
    public String delete(Student student) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE std_id = %s;", table, student.getId());

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.executeUpdate();

        return "Deleted!";
    }
}
