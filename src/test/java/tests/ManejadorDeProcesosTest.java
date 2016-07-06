package tests;

import adapters.AdapterMail;
import adapters.CentroDTO;
import eventosBusqueda.Busqueda;
import eventosBusqueda.NotificadorEmail;
import fixtures.FixtureCGP;
import fixtures.FixtureCentroDTO;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import procesos.Accion;
import procesos.CriterioDeManejoDeError;
import procesos.CriterioSinManejoDeError;
import procesos.ManejadorDeProcesos;
import procesos.Proceso;
import procesos.ResultadoEjecucion;;

public class ManejadorDeProcesosTest {
	private Accion accionValida;
	private ManejadorDeProcesos manejadorProcesos;
	private ResultadoEjecucion resultadoEjecucion;

	@Before
	public void init() {
		resultadoEjecucion = new ResultadoEjecucion(0, DateTime.now(), null);
		accionValida = mock(Accion.class);
		manejadorProcesos = ManejadorDeProcesos.getInstance();
		manejadorProcesos.vaciarListaProcesosEjecutados();

		try {
			when(accionValida.ejecutar()).thenReturn(resultadoEjecucion);
		} catch (Exception e) {

		}

	}

	@Test
	public void testSiEjecutoUnProcesoSinRetardoYNuncaEjecutoNadaEntoncesSeEjecutaEnEseMomento() {

		DateTime tiempoDeEjecucion = DateTime.now();
		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoDeEjecucion, null);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		Proceso procesoEjecutado = manejadorProcesos.getProcesosEjecutados().get(0);
		// 1000 milisegundos lo ponemos como un margen de error entre el tiempo
		// de creacion y el tiempo al terminar la ejecucion
		Assert.assertEquals(procesoEjecutado.getFechaYhoraDeEjecucion().getMillis(), tiempoDeEjecucion.getMillis(), 0);
	}

	@Test
	public void testSiEjecutoUnProcesoConRetardoYNuncaEjecutoNadaEntoncesSeEjecutaLuegoDelRetardoDefinido() {

		DateTime tiempoDeEjecucion = DateTime.now().plusMillis(2000);

		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoDeEjecucion, null);
		Assert.assertTrue(manejadorProcesos.getProcesosEjecutados().isEmpty());
		try {
			Thread.sleep(2002);
		} catch (InterruptedException e) {
		}
		// dejo 2 ms de margen para que ejecute el hilo
		Proceso procesoEjecutado = manejadorProcesos.getProcesosEjecutados().get(0);

		Assert.assertEquals(procesoEjecutado.getFechaYhoraDeEjecucion().getMillis(), tiempoDeEjecucion.getMillis(), 0);
	}

	@Test
	public void testSiEjecutoUnProcesoConMenorTiempoDeRetardoDeLosQueEstabanEnLaListaDeEjecutadosEntoncesSeEjecutaPrimero() {

		DateTime tiempoEjecucionProcesoEntrante = DateTime.now();
		DateTime tiempoEjecucionProcesoEnLista = DateTime.now().plusMillis(1000);

		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoEjecucionProcesoEnLista, null);
		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoEjecucionProcesoEntrante, null);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		List<Proceso> listaProcesosEjecutados = manejadorProcesos.getProcesosEjecutados();
		Assert.assertEquals(listaProcesosEjecutados.get(0).getFechaYhoraDeEjecucion().getMillis(),
				tiempoEjecucionProcesoEntrante.getMillis(), 0);
	}

}