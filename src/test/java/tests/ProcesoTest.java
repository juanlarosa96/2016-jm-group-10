package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import procesos.Accion;
import procesos.CriterioDeManejoDeError;
import procesos.ExceptionErrorEjecucionDeAccion;
import procesos.Proceso;
import procesos.ResultadoEjecucion;

public class ProcesoTest {

	private Accion accionExitosa;
	private Accion accionConExcepcion;
	private CriterioDeManejoDeError manejadorError;
	private Proceso proceso;

	@Before
	public void init() {
		accionExitosa = mock(Accion.class);
		accionConExcepcion = mock(Accion.class);
		manejadorError = mock(CriterioDeManejoDeError.class);
	}

	@Test
	public void testElProcesoEjecutaUnaAccionSinExcepcionYSeGuardaSuResultadoSinLlamarAManejarError() throws ExceptionErrorEjecucionDeAccion {
			when(accionExitosa.ejecutar()).thenReturn(new ResultadoEjecucion(0, DateTime.now(), "Resultado Valido"));
			proceso = new Proceso(accionExitosa, 0.0, DateTime.now(), manejadorError);
			proceso.ejecutar();
			Assert.assertFalse(proceso.getResultadoEjecucion() == null);
			verify(manejadorError, Mockito.times(0)).manejarError(proceso);

	}

	@Test
	public void testElProcesoEjecutaUnaAccionConExcepcionEInvocaAlManejadorDeError() throws ExceptionErrorEjecucionDeAccion {
			when(accionConExcepcion.ejecutar()).thenThrow(new ExceptionErrorEjecucionDeAccion());
			proceso = new Proceso(accionConExcepcion, 0.0, DateTime.now(), manejadorError);
			proceso.ejecutar();
			verify(manejadorError, Mockito.times(1)).manejarError(proceso);
	}

}
