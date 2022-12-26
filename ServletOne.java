
import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletOne")

public class ServletOne extends HttpServlet {
    private static final long serialVersionUID = 1L;

    DatabaseDAO db = new DatabaseDAO();
    Connection conn = db.connToDataBase("postgres", "postgres", "1223334444");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        db.readData(conn, "students");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        db.insertRow(conn, "students", 5, "Joseph Joestar", "PE");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        db.updateName(conn, "students", "Roach Woman", "Spider Man");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        db.deleteRow(conn, "students", "Joseph Joestar");
    }

}
