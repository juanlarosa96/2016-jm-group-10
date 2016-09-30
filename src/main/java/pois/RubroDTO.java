package pois;

import javax.persistence.Embeddable;

@Embeddable
public class RubroDTO {
	
	private String nombreRubro;
	private Double condicionDeCercania;
	
	@SuppressWarnings("unused")
	private RubroDTO(){}
	
	public RubroDTO(String nombre, Double radioDeCercania) {
		this.nombreRubro = nombre;
		this.condicionDeCercania = radioDeCercania;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public Double getCondicionDeCercania() {
		return condicionDeCercania;
	}
	
	


	
	
}
