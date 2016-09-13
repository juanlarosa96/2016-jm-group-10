package pois;

import javax.persistence.Embeddable;

import org.uqbar.geodds.Point;

@Embeddable
public class Posicion extends Point {

	public Posicion(double latitud, double longitud) {
		super(latitud, longitud);
	}

}
