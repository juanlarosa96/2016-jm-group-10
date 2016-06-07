package tpaPOIs;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public class ManejadorDeFechas {
	public static Boolean estaEnFranjaHoraria(DateTime fecha, FranjaHoraria franjaHoraria) {

		Integer diaDeLaSemana = franjaHoraria.getDiaDeLaSemana();
		LocalTime horarioApertura = franjaHoraria.getHorarioApertura();
		LocalTime horarioCierre = franjaHoraria.getHorarioCierre();

		if (fecha.getDayOfWeek() == diaDeLaSemana) {

			return estaElHorarioContenidoEnElIntervalo(horarioApertura.getMillisOfDay(), horarioCierre.getMillisOfDay(),
					fecha.getMillisOfDay());
		} else
			return false;

	}

	private static Boolean estaElHorarioContenidoEnElIntervalo(Integer apertura, Integer cierre, Integer horario) {
		return (horario >= apertura) && (horario < cierre);
	}

	public static Boolean sonIguales(FranjaHoraria franja1, FranjaHoraria franja2) {

		return franja1.getDiaDeLaSemana() == franja2.getDiaDeLaSemana()
				&& franja1.getHorarioApertura().equals(franja2.getHorarioApertura())
				&& franja1.getHorarioCierre().equals(franja2.getHorarioCierre());
	}

	public static Double obtenerDuracionIntervaloEnSegundos(DateTime inicio, DateTime fin) {
		return (double) (fin.getMillis()-inicio.getMillis())/1000;
	}
}
