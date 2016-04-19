package POI;

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

	public static void setListaPois(List<POI> listaPois) {
		Dispositivo.listaPois = listaPois;
	}

	public static void agregarPoi(POI poi) {
		listaPois.add(poi);
	}

	public Double distanciaEntrePois(POI unPoi, POI otroPoi) {

		return unPoi.distanciaAPoi(otroPoi);

	}

	public List<POI> buscarPOIs(String descripcion) {
		return listaPois.stream().filter(poi -> poi.contiene(descripcion)).collect(Collectors.toList());

	}

	public Boolean poiDisponible(POI poi, DateTime momento) {
		return poi.estaDisponible(momento);
	}
	
	public List<POI> buscarPoisDisponibles (String descripcion, DateTime momento){
		//no sirve para buscar si esta disponible un servicio en un cgp
		return buscarPOIs(descripcion).stream().filter(poi -> poi.estaDisponible(momento)).collect(Collectors.toList());
	}
	
	
	 public List<POI> buscarServicioDisponible (String servicio, DateTime momento){
	 //todos los pois que no sean cgps responden false a estaDisponibleServicio
		return buscarPOIs(servicio).stream().filter(poi -> poi.estaDisponibleServicio(servicio,momento)).collect(Collectors.toList());
		
	}	
		
		
	public Boolean estoyCercaDe(POI poi) {
		return poi.estasCerca(posicion);

	}

}
