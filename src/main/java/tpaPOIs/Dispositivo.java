package tpaPOIs;

import java.util.List;

import org.uqbar.geodds.Point;

public class Dispositivo {
	private Point posicion;
	private ManejadorDePois manejadorDePois;


	public Dispositivo(Point unaPosicion) {
		this.posicion = unaPosicion;
		this.manejadorDePois=ManejadorDePois.getInstance();
	}
	
	public Boolean estoyCercaDe(POI poi) {
		return poi.estasCerca(this.posicion);
	}
	
	public List<POI> buscarPOIs(String descripcion){
		return manejadorDePois.buscarPOIs(descripcion);
	}
}
