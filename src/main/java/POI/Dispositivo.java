package POI;

import java.util.List;
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
	 * 
	 * boolean poiDisponible(POI poi){
	 * 
	 * 
	 * }
	 * 
	 * boolean estoyCercaDe(POI poi) {
	 * 
	 * 
	 * }
	 */

}
