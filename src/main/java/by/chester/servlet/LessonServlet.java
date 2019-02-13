package by.chester.servlet;
import by.chester.dao.DaoFactory;
import by.chester.dao.PersistException;
import by.chester.entities.Lesson;
import by.chester.mySqlDAO.MySqlDaoFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/lesson")
public class LessonServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
            DaoFactory factory = new MySqlDaoFactory();
            List<Lesson> lessons = factory.getMySqlLessonDao().getAll();
            pw.println("<B>Все предметы</B>");
            pw.println(" <table border=1> ");
            pw.println("<tr>");
            pw.println("<td>Id</td>");
            pw.println("<td>Lesson</td>");
            pw.println("</tr>");
            for (Lesson ls : lessons) {
                pw.println("<tr>");
                pw.println("<td>" + ls.getId() + "</td>");
                pw.println("<td>" + ls.getLesson() + "</td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
        pw.close();
    }
}
