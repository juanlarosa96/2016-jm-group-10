package adapters;

import java.util.List;

public class CentroDTO {
	
	private Integer numeroComunaCentroDTO;
	private String domicilioCentroDTO;
	private List<ServicioDTO> serviciosDTO;
	
	@SuppressWarnings("unused")
	private String zonas;
	@SuppressWarnings("unused")
	private String nombreDirector;
	@SuppressWarnings("unused")
	private String telefono;
	
	public CentroDTO(Integer numeroComunaCentroDTO, String zonas, String nombreDirector, String domicilioCentroDTO,
			String telefono, List<ServicioDTO> serviciosDTO) {
		this.numeroComunaCentroDTO = numeroComunaCentroDTO;
		this.zonas = zonas;
		this.nombreDirector = nombreDirector;
		this.domicilioCentroDTO = domicilioCentroDTO;
		this.telefono = telefono;
		this.serviciosDTO = serviciosDTO;
	}

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
