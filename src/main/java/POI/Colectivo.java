package POI;

import org.joda.time.DateTime;

public class Colectivo extends POI {

	public boolean estaDisponibleEn(DateTime momento) {
		return true;
	}

	public Double condicionDeCercania() {
		return 0.1;
	}
}
