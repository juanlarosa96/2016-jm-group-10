package herramientas;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import pois.FranjaHoraria;

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
		return (double) (fin.getMillis() - inicio.getMillis()) / 1000;
	}

	public static String convertirFechaAString(DateTime unaFecha) {

		return Integer.toString(unaFecha.getDayOfMonth()) + "/" + Integer.toString(unaFecha.getMonthOfYear()) + "/"
				+ Integer.toString(unaFecha.getYear());

	}

	public static List<String> convertirListaFranjaHorariaAListaString(List<FranjaHoraria> lista) {
		List<String> horarios = lista.stream().map(h->convertirFranjaHorariaAString(h)).collect(Collectors.toList());
		return horarios;
	}

	public static String convertirFranjaHorariaAString(FranjaHoraria franja) {
		String cadena = "";

		cadena = cadena.concat(new Integer(franja.getDiaDeLaSemana()).toString());
		cadena = cadena.concat("-");
		cadena = cadena.concat(franja.getHorarioApertura().hourOfDay().getAsString());
		cadena = cadena.concat("-");
		cadena = cadena.concat(franja.getHorarioApertura().minuteOfHour().getAsString());
		cadena = cadena.concat("-");
		cadena = cadena.concat(franja.getHorarioCierre().hourOfDay().getAsString());
		cadena = cadena.concat("-");
		cadena = cadena.concat(franja.getHorarioCierre().minuteOfHour().getAsString());

		return cadena;

	}

	public static FranjaHoraria convertirStringAFranjaHoraria(String string) {

		String[] cadena = string.split("-");
		Integer dia = Integer.valueOf(cadena[0]);
		Integer hora = Integer.valueOf(cadena[1]);
		Integer minuto = Integer.valueOf(cadena[2]);
		LocalTime apertura = new LocalTime(hora, minuto);
		hora = Integer.valueOf(cadena[3]);
		minuto = Integer.valueOf(cadena[4]);
		LocalTime cierre = new LocalTime(hora, minuto);

		return new FranjaHoraria(dia, apertura, cierre);

	}

}
