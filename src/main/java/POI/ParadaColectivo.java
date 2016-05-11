package POI;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalTime;
import org.uqbar.geodds.Point;

public class ParadaColectivo extends POI {
	private Integer linea;

	public ParadaColectivo(Integer unaLinea, Point posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.linea = unaLinea;
		this.nombre = nombre;
		this.setHorarios(new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(2, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(3, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(4, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(5, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(6, new LocalTime(0, 0), new LocalTime(23, 59)));
				add(new FranjaHoraria(7, new LocalTime(0, 0), new LocalTime(23, 59)));
			}
		});
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
