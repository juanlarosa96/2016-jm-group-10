package procesos;

import org.joda.time.DateTime;

public class CriterioSinManejoDeError implements CriterioDeManejoDeError {

	@Override
	public void manejarError(Proceso proceso) {
		// no hace nada

		ResultadoEjecucion resultadoEjecucion = new ResultadoEjecucion(0, DateTime.now(), "Error. No se maneja");
		proceso.setResultadoEjecucion(resultadoEjecucion);
	}

}
