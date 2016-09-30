package pois;

import java.util.List;

public class ServicioDTO {
	private String nombre;

	private List<FranjaHoraria> horarios;
	
	//-------------------------

	@SuppressWarnings("unused")
	private ServicioDTO() {
	}

	public ServicioDTO(String nombre, List<FranjaHoraria> horarios) {
		this.nombre = nombre;
		this.horarios = horarios;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHorarios(List<FranjaHoraria> horarios) {
		this.horarios = horarios;
	}

	public String getNombre() {
		return nombre;
	}

	public List<FranjaHoraria> getHorarios() {
		return horarios;
	}
	
	
}
