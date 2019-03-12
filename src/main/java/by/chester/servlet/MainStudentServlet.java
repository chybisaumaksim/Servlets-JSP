package by.chester.servlet;

import by.chester.dao.PersistException;
import by.chester.dao.StudentDao;
import by.chester.entities.Student;
import by.chester.mySqlDAO.MySqlDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/main")
@WebListener
public class MainStudentServlet extends HttpServlet implements HttpSessionListener {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = checkAction(req);
        HttpSession session = req.getSession();
        StudentDao studentDao = (StudentDao) session.getAttribute("studentDao");
        if (action == "getAll") {
            getAllStudents(req, resp, studentDao);
        } else if (action == "Create") {
            createStudent(req, resp, studentDao);
        } else if (action == "Update") {
            updateStudent(req, resp, studentDao);
        } else if (action == "Delete") {
            deleteStudent(req, resp, studentDao);
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) throws ServletException, IOException {
        try {
            Student st5 = new Student();
            String id = req.getParameter("id");
            if (id == null) {
                req.setAttribute("message", "Welcome");
            } else if (validationId(id)) {
                st5.setId(Integer.parseInt(id));
                studentDao.delete(st5);
                req.setAttribute("message", "Thank You");
            } else {
                req.setAttribute("message", "Incorrect format of ID");
            }
            getServletContext().getRequestDispatcher("/StudentFrameDelete.jsp").forward(req, resp);
        } catch (PersistException | NumberFormatException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) {
        try {
            Student st4 = new Student();
            List<String> messages = new ArrayList<>();
            if (req.getParameter("id") == null) {
                req.setAttribute("message", "Welcome");
            } else if (validationId(req.getParameter("id"))
                    && validationNameAndSurname(req.getParameter("name"))
                    && validationNameAndSurname(req.getParameter("surname"))
                    && validationBirthDate(req.getParameter("birthDate"))
                    && validationYear(req.getParameter("enterYear"))) {
                st4.setId(Integer.parseInt(req.getParameter("id")));
                st4.setName(req.getParameter("name"));
                st4.setSurname(req.getParameter("surname"));
                st4.setBirthDate(req.getParameter("birthDate"));
                st4.setEnterYear(Integer.parseInt(req.getParameter("enterYear")));
                studentDao.update(st4);
                req.setAttribute("message", "Thank You");
            } else {
                messages.add("incorrect format of ID");
            }
            if (req.getParameter("name") != null && !validationNameAndSurname(req.getParameter("name"))) {
                messages.add("incorrect format of name");
            }
            if (req.getParameter("surname") != null && !validationNameAndSurname(req.getParameter("surname"))) {
                messages.add("incorrect format of surname");
            }
            if (req.getParameter("birthDate") != null && !validationBirthDate(req.getParameter("birthDate"))) {
                messages.add("incorrect format of birthDate");
            }
            if (req.getParameter("enterYear") != null && !validationYear(req.getParameter("enterYear"))) {
                messages.add("incorrect year of entry");
            }
            if (messages.size() > 0) {
                req.setAttribute("message", messages);
            }
            getServletContext().getRequestDispatcher("/StudentFrameUpdate.jsp").forward(req, resp);
        } catch (PersistException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) {
        try {
            Student st2 = new Student();
            if (req.getParameter("name") == null) {
                req.setAttribute("message", "Welcome");
            } else if (validationNameAndSurname(req.getParameter("name"))
                    && validationNameAndSurname(req.getParameter("surname"))
                    && validationBirthDate(req.getParameter("birthDate"))
                    && validationYear(req.getParameter("enterYear"))) {
                st2.setName(req.getParameter("name"));
                st2.setSurname(req.getParameter("surname"));
                st2.setBirthDate(req.getParameter("birthDate"));
                st2.setEnterYear(Integer.parseInt(req.getParameter("enterYear")));
                studentDao.create(st2);
            }
            req.setAttribute("message", "Thank You");
            List<String> messages = new ArrayList<>();
            if (req.getParameter("id") != null && !validationId(req.getParameter("id"))) {
                messages.add("incorrect format of ID");
            }
            if (req.getParameter("name") != null && !validationNameAndSurname(req.getParameter("name"))) {
                messages.add("incorrect format of name");
            }
            if (req.getParameter("surname") != null && !validationNameAndSurname(req.getParameter("surname"))) {
                messages.add("incorrect format of surname");
            }
            if (req.getParameter("birthDate") != null && !validationBirthDate(req.getParameter("birthDate"))) {
                messages.add("incorrect format of birthDate");
            }
            if (req.getParameter("enterYear") != null && !validationYear(req.getParameter("enterYear"))) {
                messages.add("incorrect year of entry");
            }
            if (messages.size() > 0) {
                req.setAttribute("message", messages);
            }
            getServletContext().getRequestDispatcher("/StudentFrameCreate.jsp").forward(req, resp);
        } catch (PersistException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllStudents(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) {
        try {
            List<Student> students = studentDao.getAll();
            req.setAttribute("students", students);
            getServletContext().getRequestDispatcher("/student.jsp").forward(req, resp);
        } catch (PersistException | ServletException | IOException e) {
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

    public boolean validationId(String id) {
        try {
            return id != null && Integer.parseInt(id) > 0
                    && Integer.parseInt(id) < Integer.MAX_VALUE;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validationNameAndSurname(String name) {
        return name != null && name.length() > 2 && name.length() < 15;
    }

    public boolean validationYear(String enterYear) {
        try {
            return enterYear != null && Integer.parseInt(enterYear) > 1900 && Integer.parseInt(enterYear) < 2030;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validationBirthDate(String birthDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(birthDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        try {
            se.getSession().setAttribute("studentDao", new MySqlDaoFactory().getMySqlStudentDao());
            System.out.println(" (session) Initial :ID = "
                    + se.getSession().getId());
        } catch (PersistException e) {
            System.err.println("Ошибка создания сессии"+e.getMessage());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        StudentDao studentDao = (StudentDao) session.getAttribute("studentDao");
        try {
            studentDao.close();
            System.out.println(" (session) Destroyed: ID= "
                    + session.getId());
        } catch (PersistException e) {
            System.err.println("Ошибка закрытия сессии"+e.getMessage());
        }
    }
}
