package pois;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;
import org.joda.time.LocalTime;
import org.mongodb.morphia.annotations.Property;

import herramientas.ManejadorDeFechas;

@Embeddable
public class FranjaHoraria {

	private Integer diaDeLaSemana;

	@Property(value = "horario_apertura")
	private LocalTime horarioApertura;

	@Property(value = "horario_cierre")
	private LocalTime horarioCierre;

	// ---------------------

	@SuppressWarnings("unused")
	private FranjaHoraria() {
	}

	public FranjaHoraria(Integer dia, LocalTime horarioApertura, LocalTime horarioCierre) {

		this.diaDeLaSemana = dia;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
	}

	public FranjaHoraria clone() {
		return new FranjaHoraria(diaDeLaSemana, horarioApertura, horarioCierre);
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

	public void setDiaDeLaSemana(Integer diaDeLaSemana) {
		this.diaDeLaSemana = diaDeLaSemana;
	}

	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public void setHorarioCierre(LocalTime horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public static List<FranjaHoraria> clonarHorarios(List<FranjaHoraria> horarios) {
		return horarios.stream().map(franja -> franja.clone()).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "Dia: " + ManejadorDeFechas.getNombreDia(this.diaDeLaSemana) + ", Hora desde: "
				+ this.horarioApertura + ", Hora hasta: " + this.horarioCierre;
	}

}
