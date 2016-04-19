package POI;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Banco extends POI {

	public Banco(Point posicion, String nombre, Direccion direccion, List<String> etiquetas) {
		this.posicion=posicion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.etiquetas = etiquetas;
	}

	private static final Integer LUNES = 1;
	private static final Integer VIERNES = 5;

	public Boolean estaDiponible(DateTime momento) {
		if (isBetween(LUNES, VIERNES, momento.getDayOfWeek()) && isBetween(10, 15, momento.getHourOfDay())) {
			return true;
		} else
			return false;
	}

	private static Boolean isBetween(int a, int b, int c) {
		return b >= a ? c >= a && c <= b : c >= b && c <= a;
	}

}
