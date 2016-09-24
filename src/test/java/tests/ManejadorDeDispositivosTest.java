package tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import adapters.CgpAdapter;
import pois.Dispositivo;
import pois.ExceptionComunaInvalida;
import pois.ManejadorDeDispositivos;
import pois.Posicion;

public class ManejadorDeDispositivosTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private ManejadorDeDispositivos manejadorDeDispositivos;
	private Dispositivo dispComuna8;
	private CgpAdapter cgpAdapter;

	@Before
	public void init() {
		manejadorDeDispositivos = ManejadorDeDispositivos.getInstance();
		dispComuna8 = new Dispositivo("dispComuna8", new Posicion(-34.674096, -58.484195));
		cgpAdapter = new CgpAdapter(null);
		manejadorDeDispositivos.setCgpAdapter(cgpAdapter);

		manejadorDeDispositivos.clearDispositivos();
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

	@Test
	public void SiFiltroPorUnaComunaValidaEnQueHayDispositivosMeLosDevuelve() throws Exception {

		manejadorDeDispositivos.agregarDispositivo(dispComuna8);
		List<Dispositivo> dispFiltrados = manejadorDeDispositivos.filtrarPorComuna(8);

		Assert.assertEquals(1, dispFiltrados.size());
		Assert.assertTrue(dispFiltrados.contains(dispComuna8));
	}

	@Test
	public void SiFiltroPorUnaComunaValidaEnQueNoHayDispositivosDevuelveListaVacia() throws Exception {
		manejadorDeDispositivos.agregarDispositivo(dispComuna8);
		List<Dispositivo> dispFiltrados = manejadorDeDispositivos.filtrarPorComuna(10);

		Assert.assertTrue(dispFiltrados.isEmpty());

	}

	@Test(expected = ExceptionComunaInvalida.class)
	public void SiFiltroPorComunaInvalidaTiraLaExcepcion() throws ExceptionComunaInvalida{
		manejadorDeDispositivos.filtrarPorComuna(57);
	}

}
