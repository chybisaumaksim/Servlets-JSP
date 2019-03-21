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
import java.util.List;

@WebServlet("/main")
@WebListener
public class MainStudentServlet extends HttpServlet implements HttpSessionListener {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (PersistException e) {
            System.err.println("Ошибка обработки запроса doGet " + e.getMessage());
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (PersistException e) {
            System.err.println("Ошибка обработки запроса doPost " + e.getMessage());
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException, PersistException {
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

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) throws ServletException, IOException, PersistException {
        try {
            Student student = new Student();
            String id = req.getParameter("id");
            List<Student> students = studentDao.getAll();
            req.setAttribute("student", students);
            if (id != null) {
                student.setId(Integer.parseInt(id));
                studentDao.delete(student);
                req.setAttribute("student", studentDao.getAll());
                req.setAttribute("messageDelete", "Запись студента удалена успешно");
            }
            getServletContext().getRequestDispatcher("/StudentFrameDelete.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + "Ошибка удаления записи студента");
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) throws PersistException, IOException, ServletException {
        try {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String birthDate = req.getParameter("birthDate");
            String enterYear = req.getParameter("enterYear");
            if (id != null) {
                req.setAttribute("student", studentDao.getById(Integer.parseInt(id)));
            }
            if (id != null && name != null && surname != null && birthDate != null
                    && enterYear != null) {
                Student student = new Student();
                student.setId(Integer.parseInt(id));
                student.setName(name);
                student.setSurname(surname);
                student.setBirthDate(birthDate);
                student.setEnterYear(Integer.parseInt(enterYear));
                req.setAttribute("student", student);
                if (validationNameAndSurname(name)) {
                    req.setAttribute("messageForName", "неверный формат имени");
                } else if (validationNameAndSurname(surname)) {
                    req.setAttribute("messageForSurname", "неверный формат фамилии");
                } else if (validationBirthDate(birthDate)) {
                    req.setAttribute("messageForBirthDate", "неверный формат дня рождения");
                } else if (validationYear(enterYear)) {
                    req.setAttribute("messageForEnterYear", "неверный формат года поступления");
                } else {
                    studentDao.update(student);
                    req.setAttribute("messageSuccess", "Запись студента обновлена успешно");
                }
            }
            getServletContext().getRequestDispatcher("/StudentFrameUpdate.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + " Ошибка обновления записи о студенте ");
        }
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) throws IOException, PersistException, ServletException {
        try {
            Student student = new Student();
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String birthDate = req.getParameter("birthDate");
            String enterYear = req.getParameter("enterYear");
            if (name != null && surname != null && birthDate != null && enterYear != null) {
                student.setName(name);
                student.setSurname(surname);
                student.setBirthDate(birthDate);
                student.setEnterYear(Integer.parseInt(enterYear));
                req.setAttribute("student", student);
                if (validationNameAndSurname(name)) {
                    req.setAttribute("messageForName", "неверный формат имени");
                } else if (validationNameAndSurname(surname)) {
                    req.setAttribute("messageForSurname", "неверный формат фамилии");
                } else if (validationBirthDate(birthDate)) {
                    req.setAttribute("messageForBirthDate", "неверный формат дня рождения");
                } else if (validationYear(enterYear)) {
                    req.setAttribute("messageForEnterYear", "неверный формат года поступления");
                } else {
                    studentDao.create(student);
                    req.setAttribute("messageSuccess", "Запись студента создана успешно");
                }
            }
            getServletContext().getRequestDispatcher("/StudentFrameCreate.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + "Ошибка создания записи о студенте");
        }
    }

    private void getAllStudents(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) throws PersistException {
        try {
            List<Student> students = studentDao.getAll();
            req.setAttribute("students", students);
            getServletContext().getRequestDispatcher("/StudentFrameGetAll.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + "Ошибка получения всех записей студентов");
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

    private boolean validationNameAndSurname(String name) {
        return name == null || name.length() <= 2 || name.length() >= 15;
    }

    private boolean validationYear(String enterYear) {
        return enterYear == null || Integer.parseInt(enterYear) <= 1900
                || Integer.parseInt(enterYear) >= 2030;
    }

    private boolean validationBirthDate(String birthDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(birthDate);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        try {
            se.getSession().setAttribute("studentDao", new MySqlDaoFactory().getMySqlStudentDao());
            System.out.println(" (session) Initial :ID = "
                    + se.getSession().getId());
        } catch (PersistException e) {
            System.err.println("Ошибка создания сессии" + e.getMessage());
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
            System.err.println("Ошибка закрытия сессии" + e.getMessage());
        }
    }
}
