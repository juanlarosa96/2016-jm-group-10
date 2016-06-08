package adapters;

import java.util.List;

public class ServicioDTO {
	private String nombre;
	private List<RangoServicioDTO> rangosHorarios;
	
	public ServicioDTO(String nombre, List<RangoServicioDTO> rangosHorarios) {
		this.nombre = nombre;
		this.rangosHorarios = rangosHorarios;
	}

	public List<RangoServicioDTO> getRangosHorarios() {
		return rangosHorarios;
	}

	public String getNombre() {
		return nombre;
	}
	
	
	
}
