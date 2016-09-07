package eventosBusqueda;

import java.util.List;

import org.joda.time.DateTime;

import pois.POI;

public class Busqueda {

	private String nombreTerminal;
	private List<POI> poisEncontrados;
	private DateTime fecha;
	private Double demoraEnSegundos;
	private String descripcionBuscada;
	
	public String getNombreTerminal() {
		return nombreTerminal;
	}

	public Integer getCantResultados() {
		return poisEncontrados.size();
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

	public Busqueda(String nombreTerminal, List<POI> listaPoisEncontrados, DateTime fecha, Double demoraEnSegundos,
			String descripcionBuscada) {
		this.nombreTerminal = nombreTerminal;
		this.poisEncontrados = listaPoisEncontrados;
		this.fecha = fecha;
		this.demoraEnSegundos = demoraEnSegundos;
		this.descripcionBuscada = descripcionBuscada;
	}
	
		
}
