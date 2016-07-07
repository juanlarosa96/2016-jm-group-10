package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
	private FalsoScheduler falsoScheduler;

	@Before
	public void init() {
		resultadoEjecucion = new ResultadoEjecucion(0, DateTime.now(), null);
		accionValida = mock(Accion.class);
		otraAccionValida = mock(Accion.class);
		manejadorProcesos = ManejadorDeProcesos.getInstance();
		manejadorProcesos.vaciarListaProcesosEjecutados();

		falsoScheduler = new FalsoScheduler();
		manejadorProcesos.setScheduler(falsoScheduler);

		try {
			when(accionValida.ejecutar()).thenReturn(resultadoEjecucion);
			when(otraAccionValida.ejecutar()).thenReturn(resultadoEjecucion);
		} catch (Exception e) {
		}

	}

	@Test
	public void testSiConfiguroUnProcesoYInicioElSchedulerLoEjecuta() {

		DateTime tiempoDeEjecucion = DateTime.now();
		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoDeEjecucion, null);
		falsoScheduler.start();
		Assert.assertEquals(1, falsoScheduler.getCantidadProcesosEjecutados(), 0);
	}

	@Test
	public void testSiConfiguroDosProcesosLosEncolaEnOrden() {

		DateTime tiempoEjecucionProceso1 = DateTime.now();
		DateTime tiempoEjecucionProceso2 = DateTime.now();

		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoEjecucionProceso1, null);
		manejadorProcesos.configurarProceso(otraAccionValida, 0.0, tiempoEjecucionProceso2, null);

		Proceso proceso1 = (Proceso) falsoScheduler.getColaProcesos().poll();
		Assert.assertTrue(proceso1.getAccion() == accionValida);

		Proceso proceso2 = (Proceso) falsoScheduler.getColaProcesos().poll();
		Assert.assertTrue(proceso2.getAccion() == otraAccionValida);

	}
	
	@Test
	public void testSiConfiguroDosProcesosEjecutaAmbos() {

		DateTime tiempoEjecucionProceso1 = DateTime.now();
		DateTime tiempoEjecucionProceso2 = DateTime.now();

		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoEjecucionProceso1, null);
		manejadorProcesos.configurarProceso(otraAccionValida, 0.0, tiempoEjecucionProceso2, null);

		falsoScheduler.start();
		Assert.assertEquals(2, falsoScheduler.getCantidadProcesosEjecutados(), 0);

	}

}
