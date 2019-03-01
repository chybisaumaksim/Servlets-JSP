package by.chester.servlet;

import by.chester.dao.DaoFactory;
import by.chester.dao.PersistException;
import by.chester.dao.StudentDao;
import by.chester.entities.Student;
import by.chester.mySqlDAO.MySqlDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class MainStudentServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String answer = checkAction(req);
        if (answer == "getAll") {
            getAllStudents(req, resp);
        }
        if (answer == "Create") {
            createStudent(req, resp);
        }
        if (answer == "Update") {
            updateStudent(req, resp);
        }
        if (answer == "Delete") {
            deleteStudent(req, resp);
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DaoFactory factory = new MySqlDaoFactory();
            StudentDao studentDao = factory.getMySqlStudentDao();
            Student st5 = new Student();
            if (req.getParameter("id") != null && validationForId(Integer.parseInt(req.getParameter("id")))) {
                st5.setId(Integer.parseInt(req.getParameter("id")));
                studentDao.delete(st5);
                req.setAttribute("wrongId", "correct");
            } else {
                req.setAttribute("wrongId", "dfghjk");
            }
            getServletContext().getRequestDispatcher("/StudentFrameDelete.jsp").forward(req, resp);
        } catch (PersistException | NumberFormatException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private boolean validationForId(int id) {
        return id > 0 && id < 1000;
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) {
        try {
            DaoFactory factory = new MySqlDaoFactory();
            StudentDao studentDao = factory.getMySqlStudentDao();
            Student st4 = new Student();
            getServletContext().getRequestDispatcher("/StudentFrameUpdate.jsp").forward(req, resp);
            st4.setId(Integer.parseInt(req.getParameter("id")));
            st4.setName(req.getParameter("name"));
            st4.setSurname(req.getParameter("surname"));
            st4.setBirthDate(req.getParameter("birthDate"));
            st4.setEnterYear(Integer.parseInt(req.getParameter("enterYear")));
            studentDao.update(st4);
        } catch (PersistException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) {
        try {
            DaoFactory factory = new MySqlDaoFactory();
            StudentDao studentDao = factory.getMySqlStudentDao();
            Student student = new Student();
            getServletContext().getRequestDispatcher("/StudentFrame.jsp").forward(req, resp);
            student.setName(req.getParameter("name"));
            student.setSurname(req.getParameter("surname"));
            student.setBirthDate(req.getParameter("birthDate"));
            student.setEnterYear(Integer.parseInt(req.getParameter("enterYear")));
            studentDao.create(student);
        } catch (PersistException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllStudents(HttpServletRequest req, HttpServletResponse resp) {
        try {
            DaoFactory factory = new MySqlDaoFactory();
            List<Student> students = factory.getMySqlStudentDao().getAll();
            req.setAttribute("students", students);
            getServletContext().getRequestDispatcher("/student.jsp").forward(req, resp);
        } catch (PersistException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String checkAction(HttpServletRequest req) {
        if (req.getParameter("getAll") != null) {
            return "getAll";
        }
        if (req.getParameter("Update") != null) {
            return "Update";
        }
        if (req.getParameter("Create") != null) {
            return "Create";
        }
        if (req.getParameter("Delete") != null) {
            return "Delete";
        }
        return null;
    }
}




