package POI;

import java.util.List;

import org.joda.time.DateTime;

public class Servicio {

	private String nombre;
	private List<FranjaHoraria> horarios;
	
	public Servicio(String nombre,List<FranjaHoraria> horarios){
		this.nombre=nombre;
		this.horarios=horarios;
	}
	

	public Boolean nombreSimilarA(String unNombre) {
		return nombre.contains(unNombre);
	}
	
	public Boolean estaDisponible(DateTime fecha) {
		return horarios.stream().anyMatch(unHorario -> unHorario.estaEnFranjaHoraria(fecha));
	}

}
