package ejb.persistence.dao.implementation;

import ejb.aop.annotation.Transactional;
import ejb.entities.Group;
import ejb.entities.Student;
import ejb.persistence.dao.GenericDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class StudentDAO implements GenericDAO<Student> {

    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public void persist(Student entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public Student get(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public void delete(Student entity) {
        entityManager.remove(entity);
    }

    @Override
    @Transactional
    public void update(Student entity) {

        Student student = entityManager
                .find(Student.class, entity.getStudentId());

        student.setDateOfBirth(entity.getDateOfBirth());
        student.setEducationYear(entity.getEducationYear());
        student.setFirstName(entity.getFirstName());
        student.setGroupId(entity.getGroupId());
        student.setPatronymic(entity.getPatronymic());
        student.setSex(entity.getSex());
        student.setSurName(entity.getSurName());
        student.setStudentId(entity.getStudentId());

        entityManager
                .merge(student);
    }

    @Override
    @Transactional
    public List<Student> getAll() {
        return entityManager
                .createNamedQuery("Student.asList", Student.class)
                .getResultList();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Student> getStudentsByGroupAndYear(Group group, int year) {
        return entityManager
                .createNativeQuery("SELECT * FROM student WHERE group_id = ? AND education_year = ?", Student.class)
                .setParameter(1, group.getGroupId())
                .setParameter(2, year)
                .getResultList();
    }

    @Transactional
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        entityManager
                .createNativeQuery("UPDATE student SET group_id =  ?, education_year = ? WHERE group_id =  ? AND  education_year = ?")
                .setParameter(1, newGroup.getGroupId())
                .setParameter(2, newYear)
                .setParameter(3, oldGroup.getGroupId())
                .setParameter(4, oldYear)
                .executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
        entityManager
                .createNativeQuery("DELETE FROM student WHERE student_id = ?")
                .setParameter(1, id)
                .executeUpdate();
    }
}
