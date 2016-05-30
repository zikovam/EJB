package ejb.persistence.dao.implementation;

import ejb.aop.annotation.Transactional;
import ejb.entities.Group;
import ejb.persistence.dao.GenericDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class GroupDAO implements GenericDAO<Group> {

    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public void update (Group entity) {
        entityManager.refresh(entity);
    }

    @Override
    @Transactional
    public void persist (Group entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public Group get (int id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    @Transactional
    public void delete (Group entity) {
        entityManager.remove(entity);
    }

    @Override
    @Transactional
    public List<Group> getAll () {
        return entityManager
                .createNamedQuery("Group.asList", Group.class)
                .getResultList();
    }
}
