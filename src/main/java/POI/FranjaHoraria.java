package POI;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public class FranjaHoraria {

	private Integer diaDeLaSemana;
	private LocalTime horaApertura;
	private LocalTime horaCierre;

	public FranjaHoraria(Integer dia, LocalTime horaApertura, LocalTime horaCierre) {

		this.diaDeLaSemana = dia;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
	}

	public Boolean estaEnFranjaHoraria(DateTime fecha) {

		if (fecha.getDayOfWeek() == diaDeLaSemana){

			return estaElHorarioContenidoEnElIntervalo(horaApertura.getMillisOfDay(), horaCierre.getMillisOfDay(), fecha.getMillisOfDay());
		}
		else
			return false;

	}
	
	
	private Boolean estaElHorarioContenidoEnElIntervalo(int apertura, int cierre, int horario) {
		return horario >= apertura && horario <= (cierre + 59999);
	}


}
