
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletOne")

public class ServletOne extends HttpServlet {
    private static final long serialVersionUID = 1L;
    StudentService service = new StudentService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String JSON_DATA = getBody(request);
            final JSONObject obj = new JSONObject(JSON_DATA);
            int std_id = obj.getInt("std_id");
            service.getStudent(std_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String JSON_DATA = getBody(request);
            final JSONObject obj = new JSONObject(JSON_DATA);
            String std_name = obj.getString("std_name");
            String std_course = obj.getString("std_course");
            service.addStudent(std_name, std_course);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            String JSON_DATA = getBody(request);
            final JSONObject obj = new JSONObject(JSON_DATA);
            int std_id = obj.getInt("std_id");
            String std_name = obj.getString("std_name");
            String std_course = obj.getString("std_course");
            service.updateStudent(std_id, std_name, std_course);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            String JSON_DATA = getBody(request);
            final JSONObject obj = new JSONObject(JSON_DATA);
            int std_id = obj.getInt("std_id");
            service.deleteStudent(std_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBody(HttpServletRequest request) throws Exception {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                }
            }
        }
        body = stringBuilder.toString();
        return body;
    }
}
