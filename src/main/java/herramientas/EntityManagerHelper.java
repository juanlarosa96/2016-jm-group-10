package herramientas;

import java.util.function.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class EntityManagerHelper {

    private static EntityManagerFactory emf;

    private static ThreadLocal<EntityManager> threadLocal;

    static {
    	try {
			emf = Persistence.createEntityManagerFactory("db");
			threadLocal = new ThreadLocal<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static EntityManager entityManager() {
    	return getEntityManager();
    }
    
    public static EntityManager getEntityManager() {
		EntityManager manager = threadLocal.get();
		if (manager == null || !manager.isOpen()) {
		    manager = emf.createEntityManager();
		    threadLocal.set(manager);
		}
		return manager;
    }

    public static void closeEntityManager() {
		EntityManager em = threadLocal.get();
		threadLocal.set(null);
		em.close();
    }

    public static void beginTransaction() {
    	EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		if(!tx.isActive()){
			tx.begin();
		}
    }

    public static void commit() {
    	EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		if(tx.isActive()){
			tx.commit();
		}

    }

    public static void rollback(){
    	EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		if(tx.isActive()){
			tx.rollback();
		}
    }
    
    public static Query createQuery(String query) {
    	return getEntityManager().createQuery(query);
    }

	public static void withTransaction(Runnable action) {
		withTransaction(() -> {
			action.run();
			return null;
		});
	}
    public static <A> A withTransaction(Supplier<A> action) {
    	beginTransaction();
    	try {
    		A result = action.get();
    		commit();
			return result;
    	} catch(Throwable e) {
    		rollback();
    		throw e;
    	}
    }

	public static void persist(Object object) {
		entityManager().persist(object);
	}

	 public static <T> T find(Class<T> entityClass, Object primaryKey){
		 return entityManager().find(entityClass, primaryKey);
	 }

	public static void flush() {
		entityManager().flush();
	}

	public static void remove(Object object) {
		entityManager().remove(object);
	}

	public static boolean contains(Object object) {
		return entityManager().contains(object);
	}
    
    

}