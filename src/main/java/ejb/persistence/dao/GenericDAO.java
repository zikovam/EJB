package ejb.persistence.dao;

import java.util.List;

public interface GenericDAO<T> {
    void update(T entity);
    void persist(T entity);
    T get(int id);
    void delete(T entity);
    List<T> getAll();
}
