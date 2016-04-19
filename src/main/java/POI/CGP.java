package POI;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class CGP extends POI {
	List<Servicio> servicios;
	Comuna comuna;

	public CGP(List<Servicio> servicios, Comuna comuna, Point posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.servicios = servicios;
		this.comuna = comuna;
		this.posicion=posicion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.etiquetas = etiquetas;
	}

	public Boolean estaDisponibleServicio(String nombreServicio, DateTime fecha) {
		Servicio servicio = buscarServicio(nombreServicio);

		if (servicio != null)
			return servicio.estaDisponible(fecha);
		else {
			System.out.println("No existe el servicio en el CGP");
			return false;
		}

	}

	private Servicio buscarServicio(String nombreServicio) {

		return servicios.stream().filter(servicio -> servicio.nombreSimilarA(nombreServicio)).findAny().get();

	}

	public Boolean estaDisponible(DateTime fecha) {

		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(fecha));
	}

	@Override
	public Boolean estasCerca(Point unaPosicion) {
		return comuna.incluyeA(unaPosicion);
	}

	public Boolean contiene(String descripcion) {
		return super.contiene(descripcion)
				|| servicios.stream().anyMatch(servicio -> servicio.nombreSimilarA(descripcion));
	}
}
