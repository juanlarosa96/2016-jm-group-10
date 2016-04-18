package POI;

import java.util.List;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class ParadaColectivo extends POI {
	private Integer linea;

	public ParadaColectivo(Integer unaLinea, Point posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.linea = unaLinea;
		this.posicion = posicion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.etiquetas = etiquetas;
	}

	public boolean estaDisponibleEn(DateTime momento) {
		return true;
	}

	public Double condicionDeCercania() {
		return 0.1;
	}
	
	public Boolean contiene(String descripcion){
		return super.contiene(descripcion) && (descripcion.contains(linea.toString()));
	}
}
