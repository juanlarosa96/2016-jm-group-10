package POI;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public class FranjaHoraria {

	private Integer diaDeLaSemana;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	
	public FranjaHoraria(Integer dia, LocalTime horaApertura, LocalTime horaCierre){
		
		this.diaDeLaSemana=dia;
		this.horaApertura=horaApertura;
		this.horaCierre=horaCierre;
	}

	public Boolean estaEnFranjaHoraria(DateTime fecha) {

		if (fecha.getDayOfWeek() == diaDeLaSemana) {

			if (isBetween(horaApertura.getMillisOfDay(), horaCierre.getMillisOfDay(), fecha.getMillisOfDay())) {
				return true;
			}

			else
				return false;

		}

		else
			return false;

	}

	private static Boolean isBetween(int a, int b, int c) {
		return b >= a ? c >= a && c <= b : c >= b && c <= a;
	}

}
