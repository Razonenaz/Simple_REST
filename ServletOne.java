
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletOne")

public class ServletOne extends HttpServlet {
    private static final long serialVersionUID = 1L;

    StudentRepository repository = new StudentRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        repository.tableName = request.getParameter("tableName");
        repository.readData();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        repository.tableName = request.getParameter("tableName");
        repository.std_id = Integer.parseInt(request.getParameter("std_id"));
        repository.std_name = request.getParameter("std_name");
        repository.std_course = request.getParameter("std_course");
        repository.insertRow();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        repository.tableName = request.getParameter("tableName");
        repository.std_name = request.getParameter("std_name");
        repository.std_id = Integer.parseInt(request.getParameter("std_id"));
        repository.updateName();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        repository.tableName = request.getParameter("tableName");
        repository.std_name = request.getParameter("std_name");
        repository.deleteRow();
    }

    /*
     * protected void doDelete(HttpServletRequest request, HttpServletResponse
     * response) throws ServletException, IOException { db.deleteRow(conn,
     * "students", "Joseph Joestar"); }
     */

}
