package POI;

import org.joda.time.DateTime;

public class Colectivo extends POI {
	private Integer linea;
	
	public Colectivo(Integer unaLinea){
		this.linea = unaLinea;
		
	}

	public boolean estaDisponibleEn(DateTime momento) {

		return true;

	}

}
