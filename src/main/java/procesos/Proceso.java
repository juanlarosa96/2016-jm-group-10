package procesos;

import org.joda.time.DateTime;

public class Proceso {

	private Accion accion;
	private Double frecuenciaEnHoras;
	private DateTime fechaYhoraDeEjecucion;
	
	public Proceso(Accion accion, Double frecuencia, DateTime fechaYhora){
		this.accion = accion;
		this.frecuenciaEnHoras = frecuencia;
		this.fechaYhoraDeEjecucion = fechaYhora;
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
	
	
	//o podrian ser dos clases que hereden de proceso (abstracta), una con frecuencia y otra sin. 
	
}
