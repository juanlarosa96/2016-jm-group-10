package tests;

import static org.mockito.Mockito.*;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import procesos.Accion;
import procesos.ExceptionErrorEjecucionDeAccion;
import procesos.ManejadorDeProcesos;
import procesos.Proceso;
import procesos.ResultadoEjecucion;
import procesos.Scheduler;
import procesos.SchedulerAdapter;;

public class ManejadorDeProcesosTest {
	private Accion accionValida;
	private Accion otraAccionValida;
	private ManejadorDeProcesos manejadorProcesos;
	private ResultadoEjecucion resultadoEjecucion;
	private Proceso proceso;
	private Scheduler scheduler;
	private SchedulerAdapter schedulerAdapter;

	@Before
	public void init(){

		resultadoEjecucion = new ResultadoEjecucion(0, DateTime.now(), null);

		accionValida = mock(Accion.class);
		otraAccionValida = mock(Accion.class);

		scheduler = mock(Scheduler.class);
		schedulerAdapter = SchedulerAdapter.getInstance();
		schedulerAdapter.setScheduler(scheduler);
		
		manejadorProcesos = ManejadorDeProcesos.getInstance();
		manejadorProcesos.vaciarListaProcesosEjecutados();

		proceso = new Proceso(accionValida, 0.0, DateTime.now(), null);

		try {
			when(accionValida.ejecutar()).thenReturn(resultadoEjecucion);
			when(otraAccionValida.ejecutar()).thenReturn(resultadoEjecucion);
		} catch (ExceptionErrorEjecucionDeAccion e) {
		}

		//when(scheduler.schedule(any(Proceso.class), anyLong(), any(TimeUnit.class), anyDouble())).thenReturn(proceso.run());

	}

	@Test
	public void testSiConfiguroUnProcesoElSchedulerLoEjecuta() {

		DateTime tiempoDeEjecucion = DateTime.now();
		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoDeEjecucion, null);

		verify(scheduler).schedule(any(Proceso.class), anyLong(), any(TimeUnit.class), anyDouble());
		Assert.assertEquals(1, manejadorProcesos.cantProcesosEjecutados(), 0);

	}

	@Test
	public void testSiConfiguroDosProcesosEjecutaAmbos() {

		DateTime tiempoEjecucionProceso1 = DateTime.now();
		DateTime tiempoEjecucionProceso2 = DateTime.now();

		manejadorProcesos.configurarProceso(accionValida, 0.0, tiempoEjecucionProceso1, null);
		manejadorProcesos.configurarProceso(otraAccionValida, 0.0, tiempoEjecucionProceso2, null);

		verify(scheduler, times(2)).schedule(any(Proceso.class), anyLong(), any(TimeUnit.class), anyDouble());

		Assert.assertEquals(2, manejadorProcesos.cantProcesosEjecutados(), 0);

	}

}
