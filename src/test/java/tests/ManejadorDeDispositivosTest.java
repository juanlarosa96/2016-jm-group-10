package tests;

import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import pois.Dispositivo;
import pois.Posicion;

public class ManejadorDeDispositivosTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void SiPersistoUnDispositivoLuegoLoEncuentro(){
		
		Dispositivo disp = new Dispositivo("unDisp", new Posicion(50.5,52.5));
		
		entityManager().persist(disp);
		
		Dispositivo dispEncontrado = entityManager().find(Dispositivo.class, disp.getId());
		
		Assert.assertTrue(disp.getNombre().equals(dispEncontrado.getNombre()));
		
		System.out.println("nombre: "+dispEncontrado.getNombre()+"; pos: "+dispEncontrado.getPosicion().toString()+"; id: "+dispEncontrado.getId().toString());
		
	}

}