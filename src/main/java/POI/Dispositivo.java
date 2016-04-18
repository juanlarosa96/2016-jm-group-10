package POI;

import java.util.List;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Dispositivo {
	private Point posicion;
	private List<POI> pois;

	public Double distanciaEntrePois(POI unPoi, POI otroPoi) {

		return unPoi.distanciaAPoi(otroPoi);

	}

	/*
	 * POI buscarPunto(String descripcion){
	 * 
	 * 
	 * }
	 */
	public boolean poiDisponible(POI poi, DateTime momento) {
		return poi.estaDisponible(momento);
	}

	public Boolean estoyCercaDe(POI poi){
		return poi.estasCerca(posicion);
		
	}

}
