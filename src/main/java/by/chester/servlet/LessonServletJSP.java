////package by.chester.servlet;
////import by.chester.dao.DaoFactory;
////import by.chester.dao.PersistException;
////import by.chester.entities.Lesson;
////import by.chester.mySqlDAO.MySqlDaoFactory;
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import java.io.IOException;
////import java.util.List;
////
////@WebServlet("/lessons")
////public class LessonServletJSP extends HttpServlet {
////
////    public void doGet(HttpServletRequest req, HttpServletResponse resp){
////        resp.setContentType("text/html;charset=utf-8");
////        try{
////            DaoFactory factory = new MySqlDaoFactory();
////            List<Lesson> lessons =factory.getMySqlLessonDao().getAll();
////            req.setAttribute("lessons", lessons);
////            getServletContext().getRequestDispatcher("/lesson.jsp").forward(req, resp);
////        }   catch (IOException | ServletException | PersistException e) {
////            e.printStackTrace();
////        }
////    }
////}
//private void createStudent(HttpServletRequest req, HttpServletResponse resp, StudentDao studentDao) throws IOException, PersistException, ServletException {
//        try {
//        Student student = new Student();
//        String name = req.getParameter("name");
//        String surname = req.getParameter("surname");
//        String birthDate = req.getParameter("birthDate");
//        String enterYear = req.getParameter("enterYear");
//        boolean nameIsValid = validationNameAndSurname(name);
//        boolean surnameIsValid = validationNameAndSurname(surname);
//        boolean birthDateIsValid = validationBirthDate(birthDate);
//        boolean yearIsValid = validationYear(enterYear);
//        if (name != null && surname != null && birthDate != null && enterYear != null) {
//        student.setName(name);
//        student.setSurname(surname);
//        student.setBirthDate(birthDate);
//        student.setEnterYear(Integer.parseInt(enterYear));
//        req.setAttribute("student", student);
//        if (nameIsValid && surnameIsValid && birthDateIsValid && yearIsValid) {
//        studentDao.create(student);
//        req.setAttribute("messageSuccess", "Запись студента создана успешно");
//        } else {
//        req.setAttribute("messageFall", "Ошибка создания записи о студенте");
//        }
//        }
//        if (req.getParameter("name") != null && !nameIsValid) {
//        req.setAttribute("messageForName", "неверный формат имени");
//        }
//        if (req.getParameter("surname") != null && !surnameIsValid) {
//        req.setAttribute("messageForSurname", "неверный формат фамилии");
//        }
//        if (req.getParameter("birthDate") != null && !birthDateIsValid) {
//        req.setAttribute("messageForBirthDate", "неверный формат дня рождения");
//        }
//        if (req.getParameter("enterYear") != null && !yearIsValid) {
//        req.setAttribute("messageForEnterYear", "неверный формат года поступления");
//        }
//        getServletContext().getRequestDispatcher("/StudentFrameCreate.jsp").forward(req, resp);
//        } catch (IOException | PersistException | ServletException e) {
//        throw new PersistException(e.getMessage() + "Ошибка создания записи о студенте");
//        }
//        }