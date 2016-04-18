package POI;

import java.util.List;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Dispositivo {
	private Point posicion;
	private static List<POI> listaPois;
	
	public Dispositivo(Point unaPosicion){
		this.posicion = unaPosicion;
	}

	public static void setListaPois(List<POI> listaPois){
		Dispositivo.listaPois = listaPois;
	}

	public static void agregarPoi(POI poi){
		listaPois.add(poi);
	}
	public Double distanciaEntrePois(POI unPoi, POI otroPoi) {

		return unPoi.distanciaAPoi(otroPoi);

	}

/*
	 public POI buscarPunto(String descripcion){
		 
	 }
*/
	public boolean poiDisponible(POI poi, DateTime momento) {
		return poi.estaDisponible(momento);
	}

	public Boolean estoyCercaDe(POI poi){
		return poi.estasCerca(posicion);
		
	}

}
