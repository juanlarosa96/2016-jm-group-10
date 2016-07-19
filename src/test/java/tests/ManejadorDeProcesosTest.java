package tests;

import static org.mockito.Mockito.*;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	private ScheduledExecutorService falsoScheduler;
	private Proceso proceso;

	@Before
	public void init() {
		resultadoEjecucion = new ResultadoEjecucion(0, DateTime.now(), null);
		accionValida = mock(Accion.class);
		otraAccionValida = mock(Accion.class);
		manejadorProcesos = ManejadorDeProcesos.getInstance();
		manejadorProcesos.vaciarListaProcesosEjecutados();

		falsoScheduler = mock(ScheduledExecutorService.class);

		proceso = new Proceso(accionValida, 0.0, DateTime.now(), null);
		manejadorProcesos.setScheduler(falsoScheduler);

		try {
			when(accionValida.ejecutar()).thenReturn(resultadoEjecucion);
			when(otraAccionValida.ejecutar()).thenReturn(resultadoEjecucion);
			when(falsoScheduler.schedule(any(Proceso.class), anyLong(),any(TimeUnit.class)))
					.then(manejadorProcesos.ejecutarProceso(proceso));
		} catch (Exception e) {
		}

	}

	@Test
	public void testSiConfiguroUnProcesoElSchedulerLoEjecuta() {

		DateTime tiempoDeEjecucion = DateTime.now();
		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoDeEjecucion, null);
		verify(falsoScheduler).schedule(any(Proceso.class), anyLong(),
				any(TimeUnit.class));
		Assert.assertEquals(1, manejadorProcesos.cantProcesosEjecutados(), 0);
	}

	@Test
	public void testSiConfiguroDosProcesosEjecutaAmbos() {

		DateTime tiempoEjecucionProceso1 = DateTime.now();
		DateTime tiempoEjecucionProceso2 = DateTime.now();

		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoEjecucionProceso1, null);
		manejadorProcesos.configurarProceso(otraAccionValida, 0.0, tiempoEjecucionProceso2, null);

		verify(falsoScheduler, times(2)).schedule(any(Proceso.class), anyLong(),
				any(TimeUnit.class));

		Assert.assertEquals(2, manejadorProcesos.cantProcesosEjecutados(), 0);

	}
}
