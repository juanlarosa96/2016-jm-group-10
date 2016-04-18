package POI;

import java.util.List;

public class CGP extends POI {
	List<Servicio> servicios;
	Comuna comuna;

	public boolean estaDisponibleServicio(String nombreServicio) {
		Servicio servicio = buscarServicio(nombreServicio);

		if (servicio != null)
			return servicio.estaDisponible();
		else {
			System.out.println("No existe el servicio en el CGP");
			return false;
		}

	}

	private Servicio buscarServicio(String nombreServicio) {
		
		return servicios.stream().filter(servicio-> servicio.nombreSimilarA(nombreServicio)).findAny().get();

	}

	public boolean estaDisponibleServicio() {

		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible());
	}
	
}
