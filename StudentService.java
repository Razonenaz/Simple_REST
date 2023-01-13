public class StudentService {

    StudentRepository sr = new StudentRepository("students");

    public void getStudent(int std_id) {
        try {
            Student student = sr.get(std_id);
            String fullInfo = String.format("%s %s %s", student.getId(), student.getName(), student.getCourse());
            DBConnection.loggerDB.info(fullInfo);
        } catch (Exception e) {
            DBConnection.loggerDB.error(e);
        }
    }

    public void addStudent(String std_name, String std_course) {
        try {
            Student student = new Student(std_name, std_course);
            sr.add(student);
        } catch (Exception e) {
            DBConnection.loggerDB.error(e);
        }
    }

    public void updateStudent(int std_id, String std_name, String std_course) {
        try {
            Student student = new Student(std_name, std_course);
            student.setId(std_id);
            sr.update(student);
            DBConnection.loggerDB.info("Row has been updated");
        } catch (Exception e) {
            DBConnection.loggerDB.info(e);
        }
    }
    public void deleteStudent(int std_id) {
        try {
            Student student = sr.get(std_id);
            sr.delete(student);
            DBConnection.loggerDB.info("Row has been deleted");
        } catch (Exception e) {
            DBConnection.loggerDB.error(e);
        }
    }
}
