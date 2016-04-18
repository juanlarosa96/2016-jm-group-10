package POI;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public class FranjaHoraria {

	private Integer dia;
	private LocalTime horaApertura;
	private LocalTime horaCierre;

	public Boolean estaEnFranjaHoraria(DateTime fecha) {

		if (fecha.getDayOfWeek() == dia) {

			if (isBetween(horaApertura.getMillisOfDay(), horaCierre.getMillisOfDay(), fecha.getMillisOfDay())) {
				return true;
			}

			else
				return false;

		}

		else
			return false;

	}

	private static boolean isBetween(int a, int b, int c) {
		return b >= a ? c >= a && c <= b : c >= b && c <= a;
	}

}
