package ejb.presentation;

import com.sun.deploy.net.HttpRequest;
import ejb.entities.Group;
import ejb.entities.MainFrameForm;
import ejb.entities.Student;
import ejb.entities.StudentForm;
import ejb.services.GroupService;
import ejb.services.StudentService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@WebServlet ("/main")
public class MainServlet extends HttpServlet {

    @Inject
    private StudentService studentService;

    @Inject
    private GroupService groupService;

    private void processRequest (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Установка кодировки для принятия параметров
        req.setCharacterEncoding("UTF-8");

        switch (getAction(req)) {
            case 1:
                addNewStudent(req);
                getServletContext()
                        .getRequestDispatcher("/WEB-INF/pages/StudentFrame.jsp")
                        .forward(req, resp);
                break;
            case 2:
                editStudent(req);
                getServletContext()
                        .getRequestDispatcher("/WEB-INF/pages/StudentFrame.jsp")
                        .forward(req, resp);
                break;
            case 3:
                String gs = req.getParameter("groupId");
                String ys = req.getParameter("year");

                moveStudentToGroup(req);

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

                if (groupId == -1) {
                    Iterator i = groups.iterator();
                    g = (Group) i.next();
                }

                Collection students = studentService
                        .getStudentsByGroupAndYear(g, year);

                form.setGroupId(g.getGroupId());
                form.setYear(year);
                form.setGroups(groups);
                form.setStudents(students);

                req.setAttribute("form", form);
                getServletContext()
                        .getRequestDispatcher("/MainFrame.jsp")
                        .forward(req, resp);
                break;
            case 4:
                deleteStudent(req);
                getServletContext()
                        .getRequestDispatcher("/WEB-INF/pages/MainFrame.jsp")
                        .forward(req, resp);
                break;
            case -1:
                break;
        }
//
//        if (answer == 1) {
//            // Тут надо сделать вызов другой формы, которая перенаправит сервлет
//            // на другую JSP для ввода данных о новом студенте
//
//            List<Group> groupList = groupService.getAll();
//
//            StudentForm sForm = new StudentForm();
//
//            sForm.initFromStudent(Student.builder()
//                    .studentId(0)
//                    .dateOfBirth(new Date())
//                    .educationYear(Calendar.getInstance().get(Calendar.YEAR))
//                    .build());
//
//            sForm.setGroups(groupService.getAll());
//
//            req.setAttribute("student", sForm);
//
//            getServletContext().getRequestDispatcher("/StudentFrame.jsp").forward(req, resp);
//
//            return;
//
//        }
//
//        if (answer == 2) {
//            // Тут надо сделать вызов другой формы, которая перенаправит сервлет
//            // на другую JSP для ввода данных о студенте
//            if (req.getParameter("studentId") != null) {
//                Student s = studentService.get((long) Integer.parseInt(req.getParameter("studentId")));
//
//                Collection groups = groupService.getAll();
//
//                StudentForm sForm = new StudentForm();
//                sForm.initFromStudent(s);
//                sForm.setGroups(groups);
//                req.setAttribute("student", sForm);
//                getServletContext().getRequestDispatcher("/StudentFrame.jsp").forward(req, resp);
//                return;
//            }
//        }
//        String gs = req.getParameter("groupId");
//        String ys = req.getParameter("year");
//
//        if (answer == 3) {
//            // Здесь мы перемещаем стедунтов в другую группу
//            String newGs = req.getParameter("newGroupId");
//            String newYs = req.getParameter("newYear");
//            try {
//                studentService
//                        .moveStudentsToGroup(Group.builder().groupId(Integer.parseInt(gs)).build(), Integer.parseInt(ys), Group.builder().groupId(Integer.parseInt(newGs)).build(), Integer.parseInt(newYs));
//                // Теперь мы будем показывать группу, куда переместили
//                gs = newGs;
//                ys = newYs;
//            } catch (SQLException sql_e) {
//                throw new IOException(sql_e.getMessage());
//            }
//        }
//
//        int groupId = -1;
//        if (gs != null) {
//            groupId = Integer.parseInt(gs);
//        }
//
//        int year = Calendar.getInstance().get(Calendar.YEAR);
//        if (ys != null) {
//            year = Integer.parseInt(ys);
//        }
//
//        MainFrameForm form = new MainFrameForm();

//        try {
//            Collection groups = groupService.getAll();
//
//            Group g = new Group();
//
//            g.setGroupId(groupId);
//            if (groupId == -1) {
//                Iterator i = groups.iterator();
//                g = (Group) i.next();
//            }
//
//            Collection students = ManagementSystem.getInstance().getStudentsFromGroup(g, year);
//
//            form.setGroupId(g.getGroupId());
//            form.setYear(year);
//            form.setGroups(groups);
//            form.setStudents(students);
//        } catch (SQLException sql_e) {
//            throw new IOException(sql_e.getMessage());
//        }

//        req.setAttribute("form", form);
//        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }

    private void deleteStudent (HttpServletRequest request) {
        studentService
                .deleteById(Integer.parseInt(request.getParameter("studentId")));
        request.setAttribute("studentList", studentService.getAllStudents());
    }

    private void moveStudentToGroup (HttpServletRequest request) {
        studentService
                .moveStudentsToGroup(Group.builder()
                        .groupId(Integer.parseInt(request.getParameter("groupId")))
                        .build(), Integer.parseInt(request.getParameter("year")), Group.builder()
                        .groupId(Integer.parseInt(request.getParameter("newGroupId")))
                        .build(), Integer.parseInt(request.getParameter("newYear")));
    }

    private void editStudent (HttpServletRequest request) {

        Student s = studentService
                .get(Integer.parseInt(request.getParameter("studentId")));

        Collection groups = groupService.getAll();

        StudentForm studentForm = new StudentForm();

        studentForm
                .initFromStudent(s);
        studentForm
                .setGroups(groups);

        request.setAttribute("student", studentForm);
    }

    private void addNewStudent (HttpServletRequest request) {

        StudentForm studentForm = new StudentForm();

        studentForm
                .initFromStudent(Student.builder()
                        .dateOfBirth(new Date())
                        .educationYear(Calendar.getInstance().get(Calendar.YEAR))
                        .build());

        studentForm
                .setGroups(groupService.getAll());

        request.setAttribute("student", studentForm);

    }

    // Здесь мы проверям какое действие нам надо сделать – и возвращаем ответ
    private int getAction (HttpServletRequest req) {
        if (req.getParameter("Add") != null) {
            return 1;
        }

        if (req.getParameter("Edit") != null) {
            return 2;
        }

        if (req.getParameter("MoveGroup") != null) {
            return 3;
        }

        if (req.getParameter("Delete") != null) {
//            if (req.getParameter("studentId") != null) {
//                studentService
//                        .delete(Student.builder().studentId(Integer.parseInt(req.getParameter("studentId"))).build());
//            }
            return 4;
        }

        return -1;
    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("studentList", studentService.getAllStudents());

        req.setAttribute("groupList", groupService.getAll());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/pages/MainFrame.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
