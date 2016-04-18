package POI;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Banco extends POI {

	private Comuna comuna;

	public Banco(Comuna comuna, Point posicion, String nombre, Direccion direccion, List<String> etiquetas) {
		this.comuna = comuna;
		this.posicion = posicion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.etiquetas = etiquetas;
	}

	private static final int VIERNES = 5;
	private static final int LUNES = 1;

	public boolean estaDiponibleEn(DateTime momento) {
		if (momento.getDayOfWeek() >= LUNES && momento.getDayOfWeek() <= VIERNES && momento.getHourOfDay() >= 10
				&& momento.getHourOfDay() <= 15) {
			return true;
		} else
			return false;
	}

	@Override
	public Boolean estasCerca(Point unaPosicion) {
		return comuna.incluyeA(unaPosicion);
	}

}
