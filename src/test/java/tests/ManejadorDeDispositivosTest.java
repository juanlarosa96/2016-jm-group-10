package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import pois.Dispositivo;

public class ManejadorDeDispositivosTest {

	@Test
	public void SiPersistoUnDispositivoLuegoLoEncuentro(){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

		Dispositivo disp = new Dispositivo("unDisp", new Point(50.0,52.0));
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		
		entityManager.persist(disp);
		
		Dispositivo dispEncontrado = entityManager.find(Dispositivo.class, disp.getId());
		
		Assert.assertTrue(disp.getNombre().equals(dispEncontrado.getNombre()));
		
		tx.rollback();
	}
	
}
