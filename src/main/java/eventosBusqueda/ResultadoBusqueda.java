package eventosBusqueda;

import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;

import pois.POI;

@Entity
public class ResultadoBusqueda {
	
	@Id @GeneratedValue
	private Integer id;
	
	private String nombreTerminal;
	
	@ManyToMany
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

	public ResultadoBusqueda(String nombreTerminal, List<POI> listaPoisEncontrados, DateTime fecha, Double demoraEnSegundos,
			String descripcionBuscada) {
		this.nombreTerminal = nombreTerminal;
		this.poisEncontrados = listaPoisEncontrados;
		this.fecha = fecha;
		this.demoraEnSegundos = demoraEnSegundos;
		this.descripcionBuscada = descripcionBuscada;
	}
	
		
}
