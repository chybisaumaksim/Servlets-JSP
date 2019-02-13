package by.chester.servlet;
import by.chester.dao.DaoFactory;
import by.chester.dao.PersistException;
import by.chester.entities.Mark;
import by.chester.mySqlDAO.MySqlDaoFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/mark")
public class MarkServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw=null;
        try {
            pw = resp.getWriter();
            DaoFactory factory = new MySqlDaoFactory();
            List<Mark> marks =factory.getMySqlMarkDao().getAll();
            pw.println("<B>Все оценки</B>");
            pw.println("<table border=1>");
            pw.println("<tr>");
            pw.println("<td>Id</td>");
            pw.println("<td>StudentId</td>");
            pw.println("<td>LessonId</td>");
            pw.println("<td>Mark</td>");
            pw.println("</tr>");
            for (Mark mk: marks) {
                pw.println("<tr>");
                pw.println("<td>" + mk.getId() + "</td>");
                pw.println("<td>" + mk.getStudentId() + "</td>");
                pw.println("<td>" + mk.getLessonId() + "</td>");
                pw.println("<td>" + mk.getMark() + "</td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
        }  catch (PersistException | IOException e) {
            e.printStackTrace();
        }
        pw.close();
    }
}

