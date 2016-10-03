package pois;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;
import org.joda.time.LocalTime;

import poisBusqueda.FranjaHorariaDTO;

@Embeddable
public class FranjaHoraria {
	
	private Integer diaDeLaSemana;
	
	private LocalTime horarioApertura;	
	private LocalTime horarioCierre;
	
	//---------------------
	
	@SuppressWarnings("unused")
	private FranjaHoraria(){}

	public FranjaHoraria(Integer dia, LocalTime horarioApertura, LocalTime horarioCierre) {

		this.diaDeLaSemana = dia;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
	}
	
	public FranjaHorariaDTO dameTuDTO(){
		return new FranjaHorariaDTO(diaDeLaSemana, horarioApertura, horarioCierre);
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
	
	public static List<FranjaHorariaDTO> obtenerFranjasHorariasDTO(List<FranjaHoraria> horarios) {
		return horarios.stream().map(franja -> franja.dameTuDTO()).collect(Collectors.toList());
	}
	

	

	
}
