package POI;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Banco extends POI {

	public Banco(Point posicion, String nombre, Direccion direccion, List<String> etiquetas) {
		this.posicion = posicion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.etiquetas = etiquetas;
	}

	private static final Integer LUNES = 1;
	private static final Integer VIERNES = 5;

	@Override
	public Boolean estaDisponible(DateTime momento) {
		//return isBetween(LUNES, VIERNES, momento.getDayOfWeek()) && isBetween(10, 14, momento.getHourOfDay())
		if (isBetween(LUNES, VIERNES, momento.getDayOfWeek()) && isBetween(10, 14, momento.getHourOfDay())) {
			return true;
		} else
			return false;
	}
	// Asumimos que el Banco cierra a las 14:59

	private static Boolean isBetween(int a, int b, int c) {
		return b >= a ? c >= a && c <= b : c >= b && c <= a;
	}

}
