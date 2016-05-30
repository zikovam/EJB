package ejb.aop;

import ejb.aop.annotation.Transactional;
import ejb.persistence.store.EntityManagerStore;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transactional
public class TransactionAdviser {

    @Inject
    private EntityManagerStore entityManagerStore;

    @AroundInvoke
    public Object advice(InvocationContext invocationContext) throws Throwable {

        EntityManager entityManager = entityManagerStore.createAndRegister();

        Object result = null;

        try {
            entityManager
                    .getTransaction()
                    .begin();
            result = invocationContext
                    .proceed();
            entityManager
                    .getTransaction()
                    .commit();
        } catch (Throwable throwable) {
            if (entityManager.getTransaction().isActive()) {
                entityManager
                        .getTransaction()
                        .rollback();
            }
            throw throwable;
        } finally {
            entityManagerStore
                    .unregister(entityManager);
            entityManager
                    .close();
        }
        return result;
    }

}
