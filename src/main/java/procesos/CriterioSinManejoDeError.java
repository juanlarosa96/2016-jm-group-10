package procesos;

public class CriterioSinManejoDeError implements CriterioDeManejoDeError {

	@Override
	public void manejarError(Proceso proceso) {

		// No maneja el error

		ResultadoEjecucion resultadoEjecucion = ResultadoEjecucion
				.dameUnResultadoDeErrorConElMensaje("Error. No se maneja.");

		proceso.setResultadoEjecucion(resultadoEjecucion);
	}

}
