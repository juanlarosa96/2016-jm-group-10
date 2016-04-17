package POI;

import org.uqbar.geodds.Point;

public abstract class POI {
	Point posicion;
	String nombre;
	Direccion direccion;

	boolean distanciaAPoiMenorA(Double distancia, POI poi) {

		return distanciaAPoi(poi) < distancia;
	}

	private Double distanciaAPoi(POI poi) {

		return posicion.distance(poi.posicion);
	}
	
	boolean esValido(){
		//chequear si es correcto comparar con null
		if(posicion!=null && nombre!=null && direccion!=null) return true;
		
		else return false;
	}
	
	Double condicionDeCercania(){
		//devuelve la cantidad de kilometros maxima que considera cerca
		
		return 0.5;
	}
}
