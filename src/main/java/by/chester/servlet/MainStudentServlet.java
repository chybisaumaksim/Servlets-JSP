package by.chester.servlet;

import by.chester.dao.PersistException;
import by.chester.dao.StudentDao;
import by.chester.entities.Student;
import by.chester.mySqlDAO.MySqlDaoFactory;
import by.chester.mySqlDAO.MySqlStudentDao;

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
        try {
            processRequest(req, resp);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (PersistException e) {
            e.printStackTrace();
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
            if (id == null) {
            } else {
                student.setId(Integer.parseInt(id));
                int sizeBefore = studentDao.getAll().size();
                studentDao.delete(student);
                req.setAttribute("student", studentDao.getAll());
                if (!(sizeBefore == studentDao.getAll().size())) {
                    req.setAttribute("message", "Запись студента удалена успешно");
                } else {
                    req.setAttribute("message", "Ошибка. Такой записи не существует");
                }
            }
            getServletContext().getRequestDispatcher("/StudentFrameDelete.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + "Ошибка удаления записи студента");
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) throws PersistException, IOException, ServletException {
        try {
            boolean nameIsValid = validationNameAndSurname(req.getParameter("name"));
            boolean surnameIsValid = validationNameAndSurname(req.getParameter("surname"));
            boolean birthDateIsValid = validationBirthDate(req.getParameter("birthDate"));
            boolean yearIsValid = validationYear(req.getParameter("enterYear"));
            String id = req.getParameter("id");
            if (!nameIsValid) {
                req.setAttribute("student", studentDao.getById(Integer.parseInt(id)));
            }
            List<String> messages = new ArrayList<>();
            if (id == null) {
            } else if (nameIsValid && surnameIsValid && birthDateIsValid && yearIsValid) {
                Student student = new Student();
                student.setId(Integer.parseInt(id));
                student.setName(req.getParameter("name"));
                student.setSurname(req.getParameter("surname"));
                student.setBirthDate(req.getParameter("birthDate"));
                student.setEnterYear(Integer.parseInt(req.getParameter("enterYear")));
                studentDao.update(student);
                req.setAttribute("student", student);
                req.setAttribute("message", "Студент обновлен успешно");
            }
            if (req.getParameter("name") != null && !nameIsValid) {
                messages.add("неверный формат имени");
            }
            if (req.getParameter("surname") != null && !surnameIsValid) {
                messages.add("неверный формат фамилии");
            }
            if (req.getParameter("birthDate") != null && !birthDateIsValid) {
                messages.add("неверный формат дня рождения");
            }
            if (req.getParameter("enterYear") != null && !yearIsValid) {
                messages.add("неверный формат года поступления");
            }
            if (messages.size() > 0) {
                req.setAttribute("message", messages);
            }
            getServletContext().getRequestDispatcher("/StudentFrameUpdate.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + "Ошибка обновления записи о студенте");
        }
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) throws IOException, PersistException, ServletException {
        try {
            Student student = new Student();
            boolean nameIsValid = validationNameAndSurname(req.getParameter("name"));
            boolean surnameIsValid = validationNameAndSurname(req.getParameter("surname"));
            boolean birthDateIsValid = validationBirthDate(req.getParameter("birthDate"));
            boolean yearIsValid = validationYear(req.getParameter("enterYear"));
            if (req.getParameter("name") == null) {
            } else if (nameIsValid && surnameIsValid && birthDateIsValid && yearIsValid) {
                student.setName(req.getParameter("name"));
                student.setSurname(req.getParameter("surname"));
                student.setBirthDate(req.getParameter("birthDate"));
                student.setEnterYear(Integer.parseInt(req.getParameter("enterYear")));
                int sizeBefore = studentDao.getAll().size();
                studentDao.create(student);
                if (!(sizeBefore == studentDao.getAll().size())) {
                    req.setAttribute("message", "Запись студента создана успешно");
                } else {
                    req.setAttribute("message", "Ошибка создания записи о студенте");
                }
            }
            List<String> messages = new ArrayList<>();
            if (req.getParameter("name") != null && !nameIsValid) {
                messages.add("неверный формат имени");
            }
            if (req.getParameter("surname") != null && !surnameIsValid) {
                messages.add("неверный формат фамилии");
            }
            if (req.getParameter("birthDate") != null && !birthDateIsValid) {
                messages.add("неверный формат дня рождения");
            }
            if (req.getParameter("enterYear") != null && !yearIsValid) {
                messages.add("неверный формат года поступления");
            }
            if (messages.size() > 0) {
                req.setAttribute("message", messages);
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
        return name != null && name.length() > 2 && name.length() < 15;
    }

    private boolean validationYear(String enterYear) {
        return enterYear != null && Integer.parseInt(enterYear) > 1900
                && Integer.parseInt(enterYear) < 2030;
    }

    private boolean validationBirthDate(String birthDate) {
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
