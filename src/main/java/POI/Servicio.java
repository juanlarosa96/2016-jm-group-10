package POI;

import java.util.List;

import org.joda.time.DateTime;

public class Servicio {

	String nombre;
	List<FranjaHoraria> horarios;

	public Boolean nombreSimilarA(String unNombre) {
		return nombre.contains(unNombre);
	}

	public Boolean estaDisponible(DateTime fecha) {
		return horarios.stream().anyMatch(unHorario -> unHorario.estaEnFranjaHoraria(fecha));
	}

}
