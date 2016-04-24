package POI;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public abstract class POI {
	//deben ser private y las subclases accederlos por getters
	protected Point posicion;
	protected String nombre;
	protected Direccion direccion;
	protected List<String> etiquetas;
	//
	public Boolean distanciaAPoiMenorA(Double distancia, POI poi) {

		return distanciaAPoi(poi) < distancia;
	}

	//no tiene sentido que sea public, debe ser private
	public Double distanciaAPoi(POI poi) {

		return posicion.distance(poi.getPosicion());
	}
	//
	public Boolean esValido() {

		if (posicion != null && nombre != null && direccion != null)
			return true;

		else
			return false;
	}

	public Boolean estaDisponible(DateTime momento) {
		return true;
	}

	public Boolean estasCerca(Point unaPosicion) {
		
		//return (posicion.distance(unaPosicion) <= this.condicionDeCercania())
		if (posicion.distance(unaPosicion) <= this.condicionDeCercania())
			return true;
		else
			return false;
	}

	public Double condicionDeCercania() {
		// devuelve la cantidad de kilometros maxima que considera cerca
		return 0.5;
	}

	public Boolean contiene(String descripcion) {
		return (descripcion.contains(nombre) || etiquetas.stream().anyMatch(palabra -> descripcion.contains(palabra)));
	}

	public Boolean estaDisponibleServicio(String servicio, DateTime momento) {
		return false;
	}

	public Point getPosicion() {
		return posicion;
	}

}