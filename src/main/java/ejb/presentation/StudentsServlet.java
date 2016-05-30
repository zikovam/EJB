package ejb.presentation;

import ejb.entities.Group;
import ejb.entities.MainFrameForm;
import ejb.entities.Student;
import ejb.services.GroupService;
import ejb.services.StudentService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

@WebServlet ("/edit")
public class StudentsServlet extends HttpServlet {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Inject
    private StudentService studentService;

    @Inject
    private GroupService groupService;

    private void processRequest (HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Установка кодировки для принятия параметров
        req.setCharacterEncoding("UTF-8");
        String sId = req.getParameter("studentId");
        // Если пользователь нажал кнопку ОК – тогда мы обновляем данные (добавляем нового студента)
        if (sId != null && req.getParameter("OK") != null) {
            try {
                // Если ID студента больше 0, то мы редактируем его данные
                if (Integer.parseInt(sId) > 0) {
                    updateStudent(req);

                } // Иначе это новый студент
                else {
                    insertStudent(req);
                }
            } catch (SQLException sql_e) {
                sql_e.printStackTrace();
                throw new IOException(sql_e.getMessage());
            } catch (ParseException p_e) {
                throw new IOException(p_e.getMessage());
            }
        }
        // А теперь опять получаем данные для отображения на главной форме
        String gs = req.getParameter("groupId");
        String ys = req.getParameter("educationYear");

        int groupId = -1;

        if (gs != null) {
            groupId = Integer.parseInt(gs);
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (ys != null) {
            year = Integer.parseInt(ys);
        }

        MainFrameForm form = new MainFrameForm();

        Collection groups = groupService.getAll();

        Group g = Group.builder().groupId(groupId).build();

        Collection students = studentService.getStudentsByGroupAndYear(g, year);

        form.setGroupId(g.getGroupId());
        form.setYear(year);
        form.setGroups(groups);
        form.setStudents(students);

        req.setAttribute("form", form);

        req.setAttribute("studentList", studentService.getAllStudents());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/pages/MainFrame.jsp")
                .forward(req, resp);
    }

    public void doGet (HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost (HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void updateStudent (HttpServletRequest req) throws SQLException, ParseException {
        studentService
                .update(prepareStudent(req));
    }

    private void insertStudent (HttpServletRequest req) throws SQLException, ParseException {
        studentService
                .persist(prepareStudent(req));
    }

    private Student prepareStudent (HttpServletRequest req) throws ParseException {

        Student.StudentBuilder sB = Student.builder()
                .firstName(req.getParameter("firstName").trim())
                .surName(req.getParameter("surName").trim())
                .patronymic(req.getParameter("patronymic").trim())
                .dateOfBirth(sdf.parse(req.getParameter("dateOfBirth").trim()));

        if (req.getParameter("sex").equals("0")) {
            sB.sex('М');
        } else {
            sB.sex('Ж');
        }
        sB.groupId(Integer.parseInt(req.getParameter("groupId").trim()))
                .educationYear(Integer.parseInt(req.getParameter("educationYear").trim()));
        return sB.build();
    }
}
