package POI;

import java.util.*;

import org.joda.time.DateTime;

public class Comercio extends POI {
	private List<FranjaHoraria> horarios;
	private Rubro rubro;
	
	public Boolean estaDisponible(DateTime fecha)
	{
		return horarios.stream().anyMatch(unHorario -> unHorario.estaEnFranjaHoraria(fecha));
	}
}
