package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class FixtureParadaColectivo {
	
	private static DateTime unHorarioCualquiera = new DateTime(2016, 4, 5, 2, 30);
	
	private static List<String> etiquetasParada114 = new ArrayList<String>() {
		{
			add("parada");
			add("114");
			add("colectivo");
		}
	};
	
	//Parada 114 Segurola
	private static Point posicionParada114Segurola = new Point(-34.631997, -58.484737);
	private static Direccion direccionParada114Segurola = new Direccion("Av. Segurola", 230, "Bacacay", "Bogota", null, null, 1407, "CABA",
			"Floresta", "CABA", "Argentina");
	
	private static ParadaColectivo parada114Segurola = new ParadaColectivo(114, posicionParada114Segurola, "114", direccionParada114Segurola,
			etiquetasParada114);

	private static Point posicionLejanaParada114Segurola = new Point(-32.631997, -60.484737);

	private static Point posicionCercanaParada114Segurola = new Point(-34.631998, -58.484736);
	
	//Parada 114 Mercedes
	private static Point posicionParada114Mercedes = new Point(-34.6334512, -58.4839027);
	private static Direccion direccionParada114Mercedes = new Direccion("Mercedes", 17, "Av. Rivadavia", "Yerbal", null, null, 1407, "CABA",
			"Floresta", "CABA", "Argentina");

	private static ParadaColectivo parada114Mercedes = new ParadaColectivo(114, posicionParada114Mercedes, "114", direccionParada114Mercedes,
			etiquetasParada114);

	//----------------------------------------------------------
	public static DateTime dameUnHorarioCualquiera() {
		return unHorarioCualquiera;
	}

	public static ParadaColectivo dameUnaParadaValida() {		
		return parada114Segurola;
	}
	
	public static ParadaColectivo dameOtraParadaValida() {
		return parada114Mercedes;
	}

	public static Point dameUnaPosicionLejanaParadaValida() {		
		return posicionLejanaParada114Segurola ;
	}
	
	public static Point dameUnaPosicionCercanaParadaValida() {
		return posicionCercanaParada114Segurola ;
	}
	
}

