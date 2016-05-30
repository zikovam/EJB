package ejb.services;

import ejb.aop.annotation.Transactional;
import ejb.entities.Group;
import ejb.entities.Student;
import ejb.persistence.dao.GenericDAO;
import ejb.persistence.dao.implementation.StudentDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class StudentService {

    @Inject
    private StudentDAO studentDAO;

    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    public void persist(Student entity) {
        studentDAO.persist(entity);
    }

    public void update(Student entity) {
        studentDAO.update(entity);
    }

    public void delete(Student entity) {
        studentDAO.delete(entity);
    }

    public Student get(int id) {
        return studentDAO.get(id);
    }

    public void moveStudentsToGroup (Group oldGroup, int oldYear, Group newGroup, int newYear) {
        studentDAO.moveStudentsToGroup(oldGroup, oldYear, newGroup, newYear);
    }

    public List<Student> getStudentsByGroupAndYear (Group group, int year) {
        return studentDAO.getStudentsByGroupAndYear(group, year);
    }

    public void deleteById (int id) {
        studentDAO.deleteById(id);
    }
}
