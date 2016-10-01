package pois;

import javax.persistence.*;

@Table(name = "rubros")
@Entity
public class Rubro {
	
	@Id @GeneratedValue
	private Integer id;
	
	private String nombreRubro;
	private Double condicionDeCercania;
	
	@SuppressWarnings("unused")
	private Rubro(){}

	public Rubro(String nombre, Double radioDeCercania) {
		this.nombreRubro = nombre;
		this.condicionDeCercania = radioDeCercania;
	}

	public Double getCondicionDeCercania() {
		return condicionDeCercania;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public void setCondicionDeCercania(Double condicionDeCercania) {
		this.condicionDeCercania = condicionDeCercania;
	}

	public RubroDTO dameTuDTO() {
		return new RubroDTO(nombreRubro, condicionDeCercania);
	}
	
}
