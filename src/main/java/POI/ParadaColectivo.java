package POI;

import java.util.List;
import org.uqbar.geodds.Point;

public class ParadaColectivo extends POI {
	private Integer linea;

	public ParadaColectivo(Integer unaLinea, Point posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.linea = unaLinea;
		this.nombre = nombre;
		this.direccion = direccion;
		this.etiquetas = etiquetas;
		this.posicion = posicion;
	}

	@Override
	public Double condicionDeCercania() {
		return 0.1;
	}
	@Override
	public Boolean contiene(String descripcion) {
		return super.contiene(descripcion) || (descripcion.contains(linea.toString()));
	}
}
