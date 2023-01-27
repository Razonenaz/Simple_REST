import java.sql.SQLException;
import java.util.ArrayList;

public class StudentService {

    private StudentRepository sr = new StudentRepository("students");

    public void getStudent(int id) {
        try {
            Student student = sr.get(id);
            DBConnection.loggerDB.info(student.toString());
        } catch (SQLException e) {
            DBConnection.loggerDB.error(e);
        }
    }

    public void getAllStudents() {
        try {
            ArrayList<Student> list = sr.getAll();
            for (Student student : list) {
                DBConnection.loggerDB.info(student.toString());
            }
        } catch (SQLException e) {
            DBConnection.loggerDB.error(e);
        }
    }

    public void addStudent(Student student) {
        try {
            sr.add(student);
        } catch (SQLException e) {
            DBConnection.loggerDB.error(e);
        }
    }

    public void updateStudent(int id, Student student) {
        try {
            student.setId(id);
            sr.update(student);
        } catch (Exception e) {
            DBConnection.loggerDB.info(e);
        }
    }
    public void deleteStudent(int id) {
        try {
            Student student = sr.get(id);
            DBConnection.loggerDB.info(sr.delete(student));
        } catch (SQLException e) {
            DBConnection.loggerDB.error(e);
        }
    }
}
