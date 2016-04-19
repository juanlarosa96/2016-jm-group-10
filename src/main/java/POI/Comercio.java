package POI;

import java.util.*;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Comercio extends POI {

	private List<FranjaHoraria> horarios;
	private Rubro rubro;

	public Comercio(Rubro unRubro, List<FranjaHoraria> losHorarios, Point posicion, String nombre, Direccion direccion, List<String> etiquetas) {
		this.rubro = unRubro;
		this.horarios = losHorarios;
		this.posicion = posicion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.etiquetas = etiquetas;
	}
	@Override
	public Double condicionDeCercania() {
		return rubro.getCondicionDeCercania();
	}
	@Override
	public Boolean estaDisponible(DateTime fecha) {
		return horarios.stream().anyMatch(unHorario -> unHorario.estaEnFranjaHoraria(fecha));
	}
	@Override
	public Boolean contiene(String descripcion) {
		return super.contiene(descripcion) || (descripcion.contains(rubro.getNombreRubro()));
	}
}
