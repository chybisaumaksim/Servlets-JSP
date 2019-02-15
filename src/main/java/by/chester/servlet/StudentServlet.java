package by.chester.servlet;
import by.chester.dao.DaoFactory;
import by.chester.dao.PersistException;
import by.chester.entities.Student;
import by.chester.mySqlDAO.MySqlDaoFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw=null;
        try {
            pw = resp.getWriter();
            DaoFactory factory = new MySqlDaoFactory();
            List<Student> students =factory.getMySqlStudentDao().getAll();
                pw.println("<B>Все студенты</B>");
                pw.println("<table border=1>");
                pw.println("<tr>");
                pw.println("<td>Id</td>");
                pw.println("<td>Name</td>");
                pw.println("<td>Surname</td>");
                pw.println("<td>BirthDate</td>");
                pw.println("<td>EnterYear</td>");
                pw.println("</tr>");
                for (Student st: students) {
                pw.println("<tr>");
                pw.println("<td>" + st.getId() + "</td>");
                pw.println("<td>" + st.getName() + "</td>");
                pw.println("<td>" + st.getSurname() + "</td>");
                pw.println("<td>" + st.getBirthDate() + "</td>");
                pw.println("<td>" + st.getEnterYear() + "</td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
        } catch (PersistException e) {
            e.printStackTrace();
        }
        pw.close();
    }
}
