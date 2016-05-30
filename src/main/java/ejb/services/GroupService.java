package ejb.services;

import ejb.entities.Group;
import ejb.persistence.dao.GenericDAO;
import ejb.persistence.dao.implementation.GroupDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class GroupService {

    @Inject
    private GroupDAO groupGenericDAO;

    public void update (Group entity) {
        groupGenericDAO.update(entity);
    }

    public Group get (int id) {
        return groupGenericDAO.get(id);
    }

    public void delete (Group entity) {
        groupGenericDAO.delete(entity);
    }

    public List<Group> getAll () {
        return groupGenericDAO.getAll();
    }

    public void persist (Group entity) {
        groupGenericDAO.persist(entity);
    }
}
