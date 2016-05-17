package tpaPOIs;

public class Rubro {
	private String nombreRubro;
	private Double condicionDeCercania;

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

}
