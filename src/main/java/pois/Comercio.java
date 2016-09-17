package pois;

import java.util.*;

import javax.persistence.*;


import herramientas.ManejadorDeStrings;


@Table(name="comercios")
@Entity
public class Comercio extends POI {
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Rubro rubro;

	@SuppressWarnings("unused")
	private Comercio(){}
	
	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public Comercio(Rubro unRubro, List<FranjaHoraria> losHorarios, Posicion posicion, String nombre, Direccion direccion,
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
