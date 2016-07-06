package procesos;

import org.joda.time.DateTime;

public class Proceso implements Runnable {

	private Accion accion;
	private Double frecuenciaEnHoras;
	private DateTime fechaYhoraDeEjecucion;
	private CriterioDeManejoDeError criterioManejoError;
	private ResultadoEjecucion resultadoEjecucion;

	public ResultadoEjecucion getResultadoEjecucion() {
		return resultadoEjecucion;
	}

	public void setResultadoEjecucion(ResultadoEjecucion resultadoEjecucion) {
		this.resultadoEjecucion = resultadoEjecucion;
	}

	public Proceso(Accion accion, Double frecuencia, DateTime fechaYhora, CriterioDeManejoDeError criterioError) {
		this.accion = accion;
		this.frecuenciaEnHoras = frecuencia;
		this.fechaYhoraDeEjecucion = fechaYhora;
		this.criterioManejoError = criterioError;
	}

	public Accion getAccion() {
		return accion;
	}

	public DateTime getFechaYhoraDeEjecucion() {
		return fechaYhoraDeEjecucion;
	}

	public Double getFrecuenciaEnHoras() {
		return frecuenciaEnHoras;
	}

	public void ejecutar() {
		try {
			ResultadoEjecucion resultadoEjecucion = accion.ejecutar();

			this.resultadoEjecucion = resultadoEjecucion;

		} catch (ExceptionErrorEjecucionDeAccion e) {

			this.criterioManejoError.manejarError(this);

		}
	}

	@Override
	public void run() {
		this.ejecutar();
		ManejadorDeProcesos.getInstance().agregarProcesoEjecutado(this);
	}

}
