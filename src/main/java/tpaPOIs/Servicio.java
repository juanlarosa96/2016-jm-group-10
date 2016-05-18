package tpaPOIs;

import java.util.List;

import org.joda.time.DateTime;

public class Servicio {

	private String nombre;
	private List<FranjaHoraria> horarios;

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
