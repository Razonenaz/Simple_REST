
import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletOne")

public class ServletOne extends HttpServlet {
    private static final long serialVersionUID = 1L;
    StudentService service = new StudentService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            service.getStudent(id);
        } else {
            service.getAllStudents();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonData = StudentController.getBody(request);
        Student student = StudentController.fromJson(jsonData);

        service.addStudent(student);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String jsonData = StudentController.getBody(request);
        Student student = StudentController.fromJson(jsonData);

        service.updateStudent(id, student);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteStudent(id);
    }
}
