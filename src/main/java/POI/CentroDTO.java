package POI;

import java.util.List;

public class CentroDTO {
	private Integer numeroComuna;
	private String zonas;
	private String nombreDirector;
	private String domicilio;
	private String telefono;
	private List<ServiciosDTO> serviciosDTO;
	
	public Integer getNumeroComuna() {
		return numeroComuna;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	
	public List<ServiciosDTO> getServiciosDTO() {
		return serviciosDTO;
	}
	
	
}
