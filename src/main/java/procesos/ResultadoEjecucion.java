package procesos;

import org.joda.time.DateTime;

public class ResultadoEjecucion {

	private Integer cantElementosAfectados;
	private DateTime fechaYhoraEjecucion;
	private String resultado;

	public ResultadoEjecucion(Integer cantElementosAfectados, DateTime fechaYhoraEjecucion, String resultado) {
		this.cantElementosAfectados = cantElementosAfectados;
		this.fechaYhoraEjecucion = fechaYhoraEjecucion;
		this.resultado = resultado;
	}

	public Integer getCantElementosAfectados() {
		return cantElementosAfectados;
	}

	public DateTime getFechaYhoraEjecucion() {
		return fechaYhoraEjecucion;
	}

	public String getResultado() {
		return resultado;
	}
	

}
