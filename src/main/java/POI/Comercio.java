package POI;

import java.util.*;
import org.joda.time.DateTime;

public class Comercio extends POI {

	private List<FranjaHoraria> horarios;
	private Rubro rubro;

	public Comercio(Rubro unRubro, List<FranjaHoraria> losHorarios) {
		this.rubro = unRubro;
		this.horarios = losHorarios;

	}

	public Double condicionDeCercania() {
		return rubro.getCondicionDeCercania();
	}

	public Boolean estaDisponible(DateTime fecha) {
		return horarios.stream().anyMatch(unHorario -> unHorario.estaEnFranjaHoraria(fecha));
	}

	public Boolean contiene(String descripcion) {
		return super.contiene(descripcion) || (descripcion.contains(rubro.getNombreRubro()));
	}
}
