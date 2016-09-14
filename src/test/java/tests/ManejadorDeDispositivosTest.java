package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;
import pois.Posicion;

import pois.Dispositivo;

public class ManejadorDeDispositivosTest {

	/*@Test
	public void SiPersistoUnDispositivoLuegoLoEncuentro(){
		
		
		Dispositivo disp = new Dispositivo("unDisp", new Posicion(50.0,52.0));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db");
		
		EntityManager entityManager = factory.createEntityManager();

		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		
		entityManager.persist(disp);
		
		Dispositivo dispEncontrado = entityManager.find(Dispositivo.class, disp.getId());
		
		Assert.assertTrue(disp.getNombre().equals(dispEncontrado.getNombre()));
		
		tx.rollback();
	}*/
	
}