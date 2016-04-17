package POI;

import org.uqbar.geodds.Point;

public abstract class POI {
	Point posicion;
	String nombre;
	Direccion direccion;

	public boolean distanciaAPoiMenorA(Double distancia, POI poi) {

		return distanciaAPoi(poi) < distancia;
	}

	public Double distanciaAPoi(POI poi) {

		return posicion.distance(poi.posicion);
	}
	
	public boolean esValido(){
		//chequear si es correcto comparar con null
		if(posicion!=null && nombre!=null && direccion!=null) return true;
		
		else return false;
	}
	
	public Double condicionDeCercania(){
		//devuelve la cantidad de kilometros maxima que considera cerca
		
		return 0.5;
	}
}
