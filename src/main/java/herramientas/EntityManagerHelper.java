package herramientas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pois.Banco;
import pois.Comercio;
import pois.Direccion;
import pois.Dispositivo;
import pois.POI;
import pois.Posicion;

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
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		if (!tx.isActive()) {
			tx.begin();
		}
	}

	public static void commit() {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		if (tx.isActive()) {
			tx.commit();
		}

	}

	public static void rollback() {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		if (tx.isActive()) {
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
		} catch (Throwable e) {
			rollback();
			throw e;
		}
	}

	public static void persistir(Object object) {
		beginTransaction();
		entityManager().persist(object);
		commit();
	}

	public static <T> T find(Class<T> entityClass, Object primaryKey) {
		return entityManager().find(entityClass, primaryKey);
	}

	public static void flush() {
		entityManager().flush();
	}

	public static void remover(Object object) {
		entityManager().remove(object);
	}

	public static boolean contains(Object object) {
		return entityManager().contains(object);
	}

	public static void clear() {
		entityManager().clear();
	}

	@SuppressWarnings("unchecked")
	public static List<POI> traerTodosLosPOIs() {
			
		return entityManager().createQuery("from POI").getResultList();
	}

	public static void actualizarPoi(POI poiNuevo, Long poiViejoID) {

		beginTransaction();
		POI poiViejo = find(POI.class, poiViejoID);
		poiViejo.copiarEstado(poiNuevo);
		flush();
		commit();

	}

	public static void eliminarPoi(POI poi) {
		beginTransaction();
		POI poiEncontrado = find(POI.class, poi.getId());
		remover(poiEncontrado);
		commit();
	}

	public static Integer actualizarEtiquetasComerciosYRetornarCantidadModificados(String nombre,
			List<String> etiquetas) {
		beginTransaction();
		List<Comercio> comercios = buscarComercios(nombre);
		comercios.stream().forEach(comercio -> comercio.setEtiquetas(etiquetas));

		flush();
		commit();

		return comercios.size();
	}

	@SuppressWarnings("unchecked")
	public static List<Comercio> buscarComercios(String nombre) {
		return createQuery("from Comercio where nombre = :nombre").setParameter("nombre", nombre).getResultList();

	}

	public static void actualizarPOIs(List<POI> listaPoisNueva) {
		beginTransaction();
		List<POI> poisViejos = traerTodosLosPOIs();
		poisViejos.stream().forEach(poi -> remover(poi));
		listaPoisNueva.stream().forEach(poi -> entityManager().persist(poi));
		commit();
	}

	@SuppressWarnings("unchecked")
	public static List<Dispositivo> traerTodosLosDispositivos() {
		return createQuery("from Dispositivo").getResultList();

	}

	public static void actualizarDispositivos(List<Dispositivo> nuevaListaDispositivos) {
		beginTransaction();

		List<Dispositivo> dispViejos = EntityManagerHelper.traerTodosLosDispositivos();
		dispViejos.stream().forEach(disp -> remover(disp));

		nuevaListaDispositivos.stream().forEach(poi -> getEntityManager().persist(poi));

		commit();
	}

	public static void removerDispositivo(Integer dispositivoID) {
		EntityManagerHelper.beginTransaction();
		Dispositivo disp = EntityManagerHelper.find(Dispositivo.class, dispositivoID);
		EntityManagerHelper.remover(disp);
		EntityManagerHelper.commit();
	}

	public static void removerTodosLosDispositivos() {
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Dispositivo").executeUpdate();
		EntityManagerHelper.commit();
	}

	public static void detach(Object entity) {
		entityManager().detach(entity);
	}

}