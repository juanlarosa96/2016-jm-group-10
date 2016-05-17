package tpaPOIs;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalTime;
import org.joda.time.DateTimeConstants;
import org.uqbar.geodds.Point;

public class Banco extends POI {

	public Banco(Point posicion, String nombre, Direccion direccion, List<String> etiquetas) {
		this.setPosicion(posicion);
		this.setNombre(nombre);
		this.setHorarios(new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(DateTimeConstants.MONDAY, new LocalTime(9, 00), new LocalTime(14, 59)));
				add(new FranjaHoraria(DateTimeConstants.TUESDAY, new LocalTime(9, 00), new LocalTime(14, 59)));
				add(new FranjaHoraria(DateTimeConstants.WEDNESDAY, new LocalTime(9, 00), new LocalTime(14, 59)));
				add(new FranjaHoraria(DateTimeConstants.THURSDAY, new LocalTime(9, 00), new LocalTime(14, 59)));
				add(new FranjaHoraria(DateTimeConstants.FRIDAY, new LocalTime(9, 00), new LocalTime(14, 59)));
				
			}
		});
		this.setDireccion(direccion);
		this.setEtiquetas(etiquetas);
	}
		
}
