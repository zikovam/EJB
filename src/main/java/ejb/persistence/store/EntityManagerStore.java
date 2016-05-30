package ejb.persistence.store;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Stack;

@ApplicationScoped
public class EntityManagerStore {

    private EntityManagerFactory entityManagerFactory;

    private ThreadLocal<Stack<EntityManager>> entityManagerThreadLocalStack;

    @PostConstruct
    public void postConstruct() {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("student");

        entityManagerThreadLocalStack = new ThreadLocal<>();
    }

    @PreDestroy
    public void preDestroy() {
        entityManagerFactory.close();
    }

    public EntityManager getEntityManager() {

        Stack<EntityManager> entityManagerStack = entityManagerThreadLocalStack.get();

        if (entityManagerStack == null || entityManagerStack.isEmpty()) {
            return null;
        } else
            return entityManagerStack.peek();
    }

    public EntityManager createAndRegister() {

        Stack<EntityManager> entityManagerStack = entityManagerThreadLocalStack.get();

        if (entityManagerStack == null) {

            entityManagerStack = new Stack<>();

            entityManagerThreadLocalStack
                    .set(entityManagerStack);
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManagerStack
                .push(entityManager);

        return entityManager;
    }

    /**
     * Removes an entity manager from the thread local stack. It needs to be created using the
     * {@link #createAndRegister()} method.
     *
     * @param entityManager - the entity manager to remove
     * @throws IllegalStateException in case the entity manager was not found on the stack
     */
    public void unregister(EntityManager entityManager) {

        Stack<EntityManager> entityManagerStack = entityManagerThreadLocalStack.get();

        if (entityManagerStack == null || entityManagerStack.isEmpty())
            throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");

        if (entityManagerStack.peek() != entityManager)
            throw new IllegalStateException("Removing of entity manager failed. Your entity manager was not found.");

        entityManagerStack.pop();
    }
}
