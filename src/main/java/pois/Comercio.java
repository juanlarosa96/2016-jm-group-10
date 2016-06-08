package pois;

import java.util.*;
import org.uqbar.geodds.Point;

import herramientas.ManejadorDeStrings;

public class Comercio extends POI {

	private Rubro rubro;

	public Comercio(Rubro unRubro, List<FranjaHoraria> losHorarios, Point posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.rubro = unRubro;
		this.setPosicion(posicion);
		this.setHorarios(losHorarios);
		this.setNombre(nombre);
		this.setDireccion(direccion);
		this.setEtiquetas(etiquetas);
	}

	@Override
	protected Double condicionDeCercania() {
		return rubro.getCondicionDeCercania();
	}

	@Override
	public Boolean condicionDeBusqueda(String descripcion) {
		return ManejadorDeStrings.estaIncluido(rubro.getNombreRubro(), descripcion);
		}
}
