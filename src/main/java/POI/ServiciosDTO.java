package POI;

import java.util.List;

public class ServiciosDTO {
	private String nombre;
	private Integer diaDeLaSemana;
	private Integer horarioDesde;
	private Integer minutoDesde;
	private Integer horarioHasta;
	private Integer minutoHasta;
	private List<Rango> rangoServicios;
	
	public String getNombre() {
		return nombre;
	}
	public List<Integer, Integer, Integer, Integer, Integer> getRangoServicios() {
		return rangoServicios;
	}
	
	
}
