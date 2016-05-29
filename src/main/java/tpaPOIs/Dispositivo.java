package tpaPOIs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Dispositivo {
	private Point posicion;
	public static List<POI> listaPois;

	public Dispositivo(Point unaPosicion) {
		this.posicion = unaPosicion;
	}
	//------Metodos para delegar en un manejador de pois, que tenga la listaDePois
	public static void setListaPois(List<POI> listaPois) {
		Dispositivo.listaPois = listaPois;
	}

	public static void agregarPoi(POI poi) {
		if(Dispositivo.estaEnLaLista(poi))
			Dispositivo.actualizarPoi(poi);			
		else
			listaPois.add(poi);
	}
	
	
	private static void actualizarPoi(POI poiNuevo) {
		
		POI poiViejo = listaPois.stream().filter(unPoi -> unPoi.esIgualA(poiNuevo)).findFirst().get();
		
		Dispositivo.eliminarPOI(poiViejo);
			
		listaPois.add(poiNuevo);
	}

	public static void eliminarPOI(POI poi) {		
		listaPois.remove(poi);		
	}

	private static boolean estaEnLaLista(POI poiBuscado) {
		return listaPois.stream().anyMatch(unPoi -> poiBuscado.esIgualA(unPoi));
		
	}

	public List<POI> buscarPOIs(String descripcion) {
		Dispositivo.consultarPoisExternos(descripcion);
		return listaPois.stream().filter(poi -> poi.contiene(descripcion)).collect(Collectors.toList());
		
	}

	private static void consultarPoisExternos(String descripcion) {
		
		Dispositivo.agregarPois(ConsultorExterno.damePoisExternos(descripcion));
		
	}
	
	private static void agregarPois(ArrayList<POI> listaDePois){		
		listaDePois.stream().forEach(poi -> Dispositivo.agregarPoi(poi));		
	}
	

	public Boolean poiDisponible(POI poi, DateTime momento) {
		return poi.estaDisponible(momento);
	}

	public List<POI> buscarPoisDisponibles(String descripcion, DateTime momento) {
		// no sirve para buscar si esta disponible un servicio en un cgp
		return buscarPOIs(descripcion).stream().filter(poi -> poi.estaDisponible(momento)).collect(Collectors.toList());
	}

	public List<POI> buscarServicioDisponible(String servicio, DateTime momento) {
		// todos los pois que no sean cgps responden false a
		// estaDisponibleServicio
		return buscarPOIs(servicio).stream().filter(poi -> poi.estaDisponibleServicio(servicio, momento))
				.collect(Collectors.toList());

	}
	//---------------------------------------------------
	public Boolean estoyCercaDe(POI poi) {
		return poi.estasCerca(this.posicion);

	}

}
