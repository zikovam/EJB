package ejb.persistence.store;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

@Stateless
public class EntityManagerDelegate implements EntityManager {

    @Inject
    private EntityManagerStore entityManagerStore;

    @Override
    public void persist(Object entity) {
        entityManagerStore
                .getEntityManager()
                .persist(entity);
    }

    @Override
    public <T> T merge(T entity) {
        return entityManagerStore
                .getEntityManager()
                .merge(entity);
    }

    @Override
    public void remove(Object entity) {
        entityManagerStore
                .getEntityManager()
                .remove(entity);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return entityManagerStore
                .getEntityManager()
                .find(entityClass, primaryKey);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
        return entityManagerStore
                .getEntityManager()
                .find(entityClass, primaryKey, properties);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
        return entityManagerStore
                .getEntityManager()
                .find(entityClass, primaryKey, lockMode);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
        return entityManagerStore
                .getEntityManager()
                .find(entityClass, primaryKey, lockMode, properties);
    }

    @Override
    public <T> T getReference(Class<T> entityClass, Object primaryKey) {
        return entityManagerStore
                .getEntityManager()
                .getReference(entityClass, primaryKey);
    }

    @Override
    public void flush() {
        entityManagerStore
                .getEntityManager()
                .flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushMode) {
        entityManagerStore
                .getEntityManager()
                .setFlushMode(flushMode);
    }

    @Override
    public FlushModeType getFlushMode() {
        return entityManagerStore
                .getEntityManager()
                .getFlushMode();
    }

    @Override
    public void lock(Object entity, LockModeType lockMode) {
        entityManagerStore
                .getEntityManager()
                .lock(entity, lockMode);
    }

    @Override
    public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        entityManagerStore
                .getEntityManager()
                .lock(entity, lockMode, properties);
    }

    @Override
    public void refresh(Object entity) {
        entityManagerStore
                .getEntityManager()
                .refresh(entity);
    }

    @Override
    public void refresh(Object entity, Map<String, Object> properties) {
        entityManagerStore
                .getEntityManager()
                .refresh(entity, properties);
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode) {
        entityManagerStore
                .getEntityManager()
                .refresh(entity, lockMode);
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        entityManagerStore
                .getEntityManager()
                .refresh(entity, lockMode, properties);
    }

    @Override
    public void clear() {
        entityManagerStore
                .getEntityManager()
                .clear();
    }

    @Override
    public void detach(Object entity) {
        entityManagerStore
                .getEntityManager()
                .detach(entity);
    }

    @Override
    public boolean contains(Object entity) {
        return entityManagerStore
                .getEntityManager()
                .contains(entity);
    }

    @Override
    public LockModeType getLockMode(Object entity) {
        return entityManagerStore
                .getEntityManager()
                .getLockMode(entity);
    }

    @Override
    public void setProperty(String propertyName, Object value) {
        entityManagerStore
                .getEntityManager()
                .setProperty(propertyName, value);
    }

    @Override
    public Map<String, Object> getProperties() {
        return entityManagerStore
                .getEntityManager()
                .getProperties();
    }

    @Override
    public Query createQuery(String qlString) {
        return entityManagerStore
                .getEntityManager()
                .createQuery(qlString);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return entityManagerStore
                .getEntityManager()
                .createQuery(criteriaQuery);
    }

    @Override
    public Query createQuery(CriteriaUpdate updateQuery) {
        return entityManagerStore
                .getEntityManager()
                .createQuery(updateQuery);
    }

    @Override
    public Query createQuery(CriteriaDelete deleteQuery) {
        return entityManagerStore
                .getEntityManager()
                .createQuery(deleteQuery);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
        return entityManagerStore
                .getEntityManager()
                .createQuery(qlString, resultClass);
    }

    @Override
    public Query createNamedQuery(String name) {
        return entityManagerStore
                .getEntityManager()
                .createNamedQuery(name);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
        return entityManagerStore
                .getEntityManager()
                .createNamedQuery(name, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString) {
        return entityManagerStore
                .getEntityManager()
                .createNativeQuery(sqlString);
    }

    @Override
    public Query createNativeQuery(String sqlString, Class resultClass) {
        return entityManagerStore
                .getEntityManager()
                .createNativeQuery(sqlString, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString, String resultSetMapping) {
        return entityManagerStore
                .getEntityManager()
                .createNativeQuery(sqlString, resultSetMapping);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
        return entityManagerStore
                .getEntityManager()
                .createNamedStoredProcedureQuery(name);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
        return entityManagerStore
                .getEntityManager()
                .createStoredProcedureQuery(procedureName);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
        return entityManagerStore
                .getEntityManager()
                .createStoredProcedureQuery(procedureName, resultClasses);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
        return entityManagerStore
                .getEntityManager()
                .createStoredProcedureQuery(procedureName, resultSetMappings);
    }

    @Override
    public void joinTransaction() {
        entityManagerStore
                .getEntityManager()
                .joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return entityManagerStore
                .getEntityManager()
                .isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> cls) {
        return entityManagerStore
                .getEntityManager()
                .unwrap(cls);
    }

    @Override
    public Object getDelegate() {
        return entityManagerStore
                .getEntityManager()
                .getDelegate();
    }

    @Override
    public void close() {
        entityManagerStore
                .getEntityManager()
                .close();
    }

    @Override
    public boolean isOpen() {
        return entityManagerStore
                .getEntityManager()
                .isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return entityManagerStore
                .getEntityManager()
                .getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerStore
                .getEntityManager()
                .getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return entityManagerStore
                .getEntityManager()
                .getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return entityManagerStore
                .getEntityManager()
                .getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
        return entityManagerStore
                .getEntityManager()
                .createEntityGraph(rootType);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String graphName) {
        return entityManagerStore
                .getEntityManager()
                .createEntityGraph(graphName);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String graphName) {
        return entityManagerStore
                .getEntityManager()
                .getEntityGraph(graphName);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
        return entityManagerStore
                .getEntityManager()
                .getEntityGraphs(entityClass);
    }
}
