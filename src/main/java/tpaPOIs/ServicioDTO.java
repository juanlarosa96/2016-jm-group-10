package tpaPOIs;

import java.util.List;

public class ServicioDTO {
	private String nombre;
	private List<RangoServicioDTO> rangosHorarios;
	
	public List<RangoServicioDTO> getRangosHorarios() {
		return rangosHorarios;
	}

	public String getNombre() {
		return nombre;
	}
	
	
	
}
