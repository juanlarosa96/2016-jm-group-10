package pois;

import java.util.List;

import org.joda.time.DateTime;

import herramientas.ManejadorDeFechas;
import herramientas.ManejadorDeStrings;

public class Servicio {

	private String nombre;
	private List<FranjaHoraria> horarios;
	
	
	public String getNombre() {
		return nombre;
	}

	public List<FranjaHoraria> getHorarios() {
		return horarios;
	}

	public Servicio(String nombre, List<FranjaHoraria> horarios) {
		this.nombre = nombre;
		this.horarios = horarios;
	}

	public Boolean nombreSimilarA(String palabra) {
		return ManejadorDeStrings.estaIncluido(palabra, nombre);
	}

	public Boolean estaDisponible(DateTime fecha) {
		return horarios.stream().anyMatch(franjaHoraria -> ManejadorDeFechas.estaEnFranjaHoraria(fecha,franjaHoraria));
	}
	
	

}
