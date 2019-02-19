package by.chester.servlet;
import by.chester.dao.DaoFactory;
import by.chester.dao.PersistException;
import by.chester.entities.Lesson;
import by.chester.mySqlDAO.MySqlDaoFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lessons")
public class LessonServletJSP extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("text/html;charset=utf-8");
        try{
            DaoFactory factory = new MySqlDaoFactory();
            List<Lesson> lessons =factory.getMySqlLessonDao().getAll();
            req.setAttribute("lessons", lessons);
            getServletContext().getRequestDispatcher("/lesson.jsp").forward(req, resp);
        }   catch (IOException | ServletException | PersistException e) {
            e.printStackTrace();
        }
    }
}
