package pois;

import java.util.List;

public class ServicioDTO {
	private String nombre;

	private List<FranjaHorariaDTO> horarios;
	
	//-------------------------

	@SuppressWarnings("unused")
	private ServicioDTO() {
	}

	public ServicioDTO(String nombre, List<FranjaHorariaDTO> horarios) {
		this.nombre = nombre;
		this.horarios = horarios;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHorarios(List<FranjaHorariaDTO> horarios) {
		this.horarios = horarios;
	}

	public String getNombre() {
		return nombre;
	}

	public List<FranjaHorariaDTO> getHorarios() {
		return horarios;
	}
	
	
}
