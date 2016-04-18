package POI;

public class Rubro {
	public String nombreRubro;
	private Double condicionDeCercania;
	public Rubro (String nombre, Double radio)
	{
		this.nombreRubro = nombre;
		this.condicionDeCercania = radio;
	}
	public Double getCondicionDeCercania() {
		return condicionDeCercania;
	}
	public String getNombreRubro() {
		return nombreRubro;
	}

}
