package tpaPOIs;

import org.uqbar.geodds.Point;

public class Dispositivo {
	private Point posicion;


	public Dispositivo(Point unaPosicion) {
		this.posicion = unaPosicion;
	}
	public Boolean estoyCercaDe(POI poi) {
		return poi.estasCerca(this.posicion);
	}

}
