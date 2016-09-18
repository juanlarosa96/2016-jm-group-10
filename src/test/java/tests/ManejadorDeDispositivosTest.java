package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import pois.Dispositivo;
import pois.ManejadorDeDispositivos;
import pois.Posicion;

public class ManejadorDeDispositivosTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private ManejadorDeDispositivos manejadorDeDispositivos;

	@Before
	public void init() {
		manejadorDeDispositivos = ManejadorDeDispositivos.getInstance();
	}

	@Test
	public void SiAgregoUnDispositivoLoEncuentroEnBD() {

		Dispositivo disp = new Dispositivo("unDisp", new Posicion(50.5, 52.5));

		manejadorDeDispositivos.agregarDispositivo(disp);

		Dispositivo dispEncontrado = entityManager().find(Dispositivo.class, disp.getId());

		Assert.assertTrue(disp.getNombre().equals(dispEncontrado.getNombre()));

		Assert.assertTrue(disp.getId() == dispEncontrado.getId());
	}

	@Test
	public void SiAgregoUnDispositivoYLuegoLoEliminoEntoncesNoLoEncuentroEnBD() {

		Dispositivo disp = new Dispositivo("unDisp", new Posicion(25.5, 25.5));

		manejadorDeDispositivos.agregarDispositivo(disp);
		manejadorDeDispositivos.eliminarDispositivo(disp);

		Dispositivo dispEncontrado = entityManager().find(Dispositivo.class, disp.getId());

		Assert.assertTrue(dispEncontrado == null);
		Assert.assertFalse(manejadorDeDispositivos.getListaDispositivos().contains(disp));
		
	}

}
