package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import procesos.Accion;
import procesos.ManejadorDeProcesos;
import procesos.Proceso;
import procesos.ResultadoEjecucion;;

public class ManejadorDeProcesosTest {
	private Accion accionValida;
	private Accion otraAccionValida;
	private ManejadorDeProcesos manejadorProcesos;
	private ResultadoEjecucion resultadoEjecucion;
	private Accion accionEsperarUnSegundo;

	@Before
	public void init() {
		resultadoEjecucion = new ResultadoEjecucion(0, DateTime.now(), null);
		accionValida = mock(Accion.class);
		otraAccionValida = mock(Accion.class);
		accionEsperarUnSegundo = mock(Accion.class);
		manejadorProcesos = ManejadorDeProcesos.getInstance();
		manejadorProcesos.vaciarListaProcesosEjecutados();
		
		try {
			when(accionEsperarUnSegundo.ejecutar());
			when(accionValida.ejecutar()).thenReturn(resultadoEjecucion);
			when(otraAccionValida.ejecutar()).thenReturn(resultadoEjecucion);
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
			Thread.sleep(2010);
		} catch (InterruptedException e) {
		}
		// dejo 10 ms de margen para que ejecute el hilo
		Proceso procesoEjecutado = manejadorProcesos.getProcesosEjecutados().get(0);

		Assert.assertEquals(procesoEjecutado.getFechaYhoraDeEjecucion().getMillis(), tiempoDeEjecucion.getMillis(), 0);
	}

	@Test
	public void testSiEjecutoUnProcesoConMenorTiempoDeRetardoDeLosQueEstanEnLaColaDeEjecucionEntoncesSeEjecutaPrimero() {

		DateTime tiempoEjecucionProcesoEntrante = DateTime.now().plusMillis(800);
		DateTime tiempoEjecucionProcesoEnLista = DateTime.now().plusMillis(1000);

		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoEjecucionProcesoEnLista, null);
		manejadorProcesos.configurarProceso(otraAccionValida, 0.0, tiempoEjecucionProcesoEntrante, null);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		List<Proceso> listaProcesosEjecutados = manejadorProcesos.getProcesosEjecutados();

		Assert.assertTrue(listaProcesosEjecutados.get(0).getAccion() == otraAccionValida);
	}

	@Test
	public void testSiEjecutoUnProcesoConIgualTiempoDeRetardoDeOtroQueEstaEnLaColaDeEjecucionEntoncesSeEjecutaPrimeroElQueEstabaEnLaCola() {

		DateTime tiempoEjecucionProcesoEnLista = DateTime.now().plusMillis(1000);
		DateTime tiempoEjecucionProcesoEntrante = DateTime.now().plusMillis(1000);
	
		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoEjecucionProcesoEnLista, null);
		manejadorProcesos.configurarProceso(otraAccionValida, 0.0, tiempoEjecucionProcesoEntrante, null);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		List<Proceso> listaProcesosEjecutados = manejadorProcesos.getProcesosEjecutados();

		Assert.assertTrue(listaProcesosEjecutados.get(0).getAccion() == accionValida);
		Assert.assertTrue(listaProcesosEjecutados.get(1).getAccion() == otraAccionValida);

	}
	@Test
	public void testSiSeConfiguraUnProcesoParaUnMomentoEnElQueHayOtroEjecutandoEsperaHastaQueTermine(){
		
		DateTime tiempoEjecucionProceso1 = DateTime.now();
		DateTime tiempoEjecucionProceso2 = DateTime.now().plusMillis(500);
		
		manejadorProcesos.configurarProceso(accionEsperarUnSegundo, 0.0, tiempoEjecucionProceso1, null);
		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoEjecucionProceso2, null);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		List<Proceso> listaProcesosEjecutados = manejadorProcesos.getProcesosEjecutados();

		Assert.assertTrue(listaProcesosEjecutados.get(0).getAccion() == accionEsperarUnSegundo);
		Assert.assertEquals(1,listaProcesosEjecutados.size(),0);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		Assert.assertTrue(listaProcesosEjecutados.get(1).getAccion() == accionValida);
	}

}