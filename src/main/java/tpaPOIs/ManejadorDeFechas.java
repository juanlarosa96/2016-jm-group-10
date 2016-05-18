package tpaPOIs;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public class ManejadorDeFechas {
	public static Boolean estaEnFranjaHoraria(DateTime fecha, FranjaHoraria franjaHoraria) {

		Integer diaDeLaSemana = franjaHoraria.getDiaDeLaSemana();
		LocalTime horarioApertura = franjaHoraria.getHorarioApertura();
		LocalTime horarioCierre = franjaHoraria.getHorarioCierre();
		
		if (fecha.getDayOfWeek() == diaDeLaSemana){

			return estaElHorarioContenidoEnElIntervalo(horarioApertura.getMillisOfDay(), horarioCierre.getMillisOfDay(), fecha.getMillisOfDay());
		}
		else
			return false;

	}
	
	
	private static Boolean estaElHorarioContenidoEnElIntervalo(Integer apertura, Integer cierre, Integer horario) {
		return (horario >= apertura) && (horario < cierre);
	}
}
