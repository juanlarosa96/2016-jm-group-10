package poisBusqueda;

import javax.persistence.*;
import org.joda.time.LocalTime;

@Embeddable
public class FranjaHorariaDTO {
	
	private Integer diaDeLaSemana;
	
	private String stringHorarioApertura;	
	private String stringHorarioCierre;
	
	//---------------------
	
	@SuppressWarnings("unused")
	private FranjaHorariaDTO(){}

	public FranjaHorariaDTO(Integer dia, LocalTime horarioApertura, LocalTime horarioCierre) {

		this.diaDeLaSemana = dia;
		this.stringHorarioApertura = horarioApertura.toString();
		this.stringHorarioCierre = horarioCierre.toString();
	}
	
	public Integer getDiaDeLaSemana() {
		return diaDeLaSemana;
	}

	public LocalTime getHorarioApertura() {
		return LocalTime.parse(stringHorarioApertura);
	}

	public LocalTime getHorarioCierre() {
		return LocalTime.parse(stringHorarioCierre);
	}

	public void setDiaDeLaSemana(Integer diaDeLaSemana) {
		this.diaDeLaSemana = diaDeLaSemana;
	}

	public void setHorarioApertura(LocalTime horarioApertura) {
		this.stringHorarioApertura = horarioApertura.toString();
	}

	public void setHorarioCierre(LocalTime horarioCierre) {
		this.stringHorarioCierre = horarioCierre.toString();
	}
	
}
