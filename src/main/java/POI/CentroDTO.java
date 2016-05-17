package POI;

import java.util.List;

public class CentroDTO {
	private Integer numeroComunaCentroDTO;
	private String zonas;
	private String nombreDirector;
	private String domicilioCentroDTO;
	private String telefono;
	private List<ServicioDTO> serviciosDTO;
	
	public Integer getNumeroComunaCentroDTO() {
		return numeroComunaCentroDTO;
	}
	
	public String getDomicilioCentroDTO() {
		return domicilioCentroDTO;
	}
	
	public List<ServicioDTO> getServiciosDTO() {
		return serviciosDTO;
	}
	
	
}
