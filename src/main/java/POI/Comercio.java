package POI;

import java.util.*;
import org.uqbar.geodds.Point;

public class Comercio extends POI {

	private Rubro rubro;

	public Comercio(Rubro unRubro, List<FranjaHoraria> losHorarios, Point posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.rubro = unRubro;
		this.posicion = posicion;
		this.setHorarios(losHorarios);
		this.nombre = nombre;
		this.direccion = direccion;
		this.etiquetas = etiquetas;
	}

	@Override
	public Double condicionDeCercania() {
		return rubro.getCondicionDeCercania();
	}

	
	@Override
	public Boolean contiene(String descripcion) {
		return super.contiene(descripcion) || (descripcion.contains(rubro.getNombreRubro()));
	}
}
