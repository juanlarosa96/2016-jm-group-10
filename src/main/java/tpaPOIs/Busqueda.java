package tpaPOIs;

import org.joda.time.DateTime;

public class Busqueda {

	private String nombreTerminal;
	private Integer cantResultados;
	private DateTime fecha;
	private Double demoraEnSegundos;
	private String descripcionBuscada;
	
	public String getNombreTerminal() {
		return nombreTerminal;
	}

	public Integer getCantResultados() {
		return cantResultados;
	}

	public DateTime getFecha() {
		return fecha;
	}

	public Double getDemoraEnSegundos() {
		return demoraEnSegundos;
	}

	public String getDescripcionBuscada() {
		return descripcionBuscada;
	}

	public Busqueda(String nombreTerminal, Integer cantResultados, DateTime fecha, Double demoraEnSegundos,
			String descripcionBuscada) {
		this.nombreTerminal = nombreTerminal;
		this.cantResultados = cantResultados;
		this.fecha = fecha;
		this.demoraEnSegundos = demoraEnSegundos;
		this.descripcionBuscada = descripcionBuscada;
	}
	
		
}
