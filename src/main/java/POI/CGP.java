package POI;

import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class CGP extends POI {
	private List<Servicio> servicios;
	private Comuna comuna;

	public CGP(List<Servicio> servicios, Comuna comuna, Point posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.servicios = servicios;
		this.comuna = comuna;
		this.setPosicion(posicion);
		this.setNombre(nombre);
		this.setDireccion(direccion);
		this.setEtiquetas(etiquetas);
	}

	@Override
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

		List<Servicio> listaServicios = servicios.stream().filter(servicio -> servicio.nombreSimilarA(nombreServicio))
				.collect(Collectors.toList());
		if (listaServicios.isEmpty()) {
			return null;
		} else {
			return listaServicios.get(0);

		}

	}

	@Override
	public Boolean estaDisponible(DateTime fecha) { 

		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(fecha));
	}

	@Override
	public Boolean estasCerca(Point unaPosicion) {
		return comuna.incluyeA(unaPosicion);
	}

	@Override
	public Boolean contiene(String descripcion) {
		return super.contiene(descripcion)
				|| servicios.stream().anyMatch(servicio -> servicio.nombreSimilarA(descripcion));
	}
	
	@Override
	public Boolean esIgualA(POI otroPoi) {
		return this.getDireccion().esLaMismaDireccionQue(otroPoi.getDireccion());		
		
	}
}
