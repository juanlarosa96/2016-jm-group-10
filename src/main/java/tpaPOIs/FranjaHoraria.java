package tpaPOIs;

import org.joda.time.LocalTime;

public class FranjaHoraria {

	private Integer diaDeLaSemana;
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;

	public FranjaHoraria(Integer dia, LocalTime horarioApertura, LocalTime horarioCierre) {

		this.diaDeLaSemana = dia;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
	}

	public Integer getDiaDeLaSemana() {
		return diaDeLaSemana;
	}

	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}

	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}

	

	
}
