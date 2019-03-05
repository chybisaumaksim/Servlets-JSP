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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet("/main")
public class MainStudentServlet extends HttpServlet {
    private StudentDao studentDao;
    private DaoFactory factory;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            factory = new MySqlDaoFactory();
            studentDao = factory.getMySqlStudentDao();
        } catch (PersistException e) {
            e.printStackTrace();
        }
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = checkAction(req);
        if (action == "getAll") {
            getAllStudents(req, resp);
        } else if (action == "Create") {
            createStudent(req, resp);
        } else if (action == "Update") {
            updateStudent(req, resp);
        } else if (action == "Delete") {
            deleteStudent(req, resp);
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Student st5 = new Student();
            String id = req.getParameter("id");
            if (validationId(id)) {
                st5.setId(Integer.parseInt(id));
                studentDao.delete(st5);
                req.setAttribute("message", "Thank You");
            } else if (id == null) {
                req.setAttribute("message", "Welcome");
            } else {
                req.setAttribute("message", "Incorrect format of ID. Try again");
            }
            getServletContext().getRequestDispatcher("/StudentFrameDelete.jsp").forward(req, resp);
        } catch (PersistException | NumberFormatException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Student st4 = new Student();
            if (validationId(req.getParameter("id"))
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
            } else if (req.getParameter("id") == null) {
                req.setAttribute("message", "Welcome");
            } else if (!validationId(req.getParameter("id"))) {
                req.setAttribute("message", "Incorrect format of ID. Try again");
            } else if (!validationNameAndSurname(req.getParameter("name"))) {
                req.setAttribute("message", "Incorrect format of name. Try again");
            } else if (!validationNameAndSurname(req.getParameter("surname"))) {
                req.setAttribute("message", "Incorrect format of surname. Try again");
            } else if (!validationBirthDate(req.getParameter("birthDate"))) {
                req.setAttribute("message", "Incorrect format of birthDate. Try again");
            } else if (!validationYear(req.getParameter("enterYear"))) {
                req.setAttribute("message", "Incorrect year of entry. Try again");
            }
            getServletContext().getRequestDispatcher("/StudentFrameUpdate.jsp").forward(req, resp);
        } catch (PersistException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Student st2 = new Student();
            if (validationNameAndSurname(req.getParameter("name"))
                    && validationNameAndSurname(req.getParameter("surname"))
                    && validationBirthDate(req.getParameter("birthDate"))
                    && validationYear(req.getParameter("enterYear"))) {
                st2.setName(req.getParameter("name"));
                st2.setSurname(req.getParameter("surname"));
                st2.setBirthDate(req.getParameter("birthDate"));
                st2.setEnterYear(Integer.parseInt(req.getParameter("enterYear")));
                studentDao.create(st2);
                req.setAttribute("message", "Thank You");
            } else if (req.getParameter("name") == null ) {
                req.setAttribute("message", "Welcome");
            } else if (!validationNameAndSurname(req.getParameter("name"))) {
                req.setAttribute("message", "Incorrect format of name. Try again");
            } else if (!validationNameAndSurname(req.getParameter("surname"))) {
                req.setAttribute("message", "Incorrect format of surname. Try again");
            } else if (!validationBirthDate(req.getParameter("birthDate"))) {
                req.setAttribute("message", "Incorrect format of birthdate. Try again");
            } else if (!validationYear(req.getParameter("enterYear"))) {
                req.setAttribute("message", "Incorrect year of entry. Try again");
            }
            getServletContext().getRequestDispatcher("/StudentFrameCreate.jsp").forward(req, resp);
        } catch (PersistException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllStudents(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Student> students = factory.getMySqlStudentDao().getAll();
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
        try {
            return name != null && name.length() > 2 && name.length() < 15;
        } catch (Exception e) {
            return false;
        }
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
}



