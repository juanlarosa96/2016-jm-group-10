package fixtures;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import pois.Posicion;

import pois.Direccion;
import pois.ParadaColectivo;

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
	private static Posicion posicionParada114Segurola = new Posicion(-34.631997, -58.484737);
	private static Direccion direccionParada114Segurola = new Direccion("Av. Segurola", 230, "Bacacay", "Bogota", null, null, 1407, "CABA",
			"Floresta", "CABA", "Argentina");
	
	private static ParadaColectivo parada114Segurola = new ParadaColectivo(114, posicionParada114Segurola, "114", direccionParada114Segurola,
			etiquetasParada114);

	private static Posicion posicionLejanaParada114Segurola = new Posicion(-32.631997, -60.484737);

	private static Posicion posicionCercanaParada114Segurola = new Posicion(-34.631998, -58.484736);
	
	//Parada 114 Mercedes
	private static Posicion posicionParada114Mercedes = new Posicion(-34.6334512, -58.4839027);
	private static Direccion direccionParada114Mercedes = new Direccion("Mercedes", 17, "Av. Rivadavia", "Yerbal", null, null, 1407, "CABA",
			"Floresta", "CABA", "Argentina");

	private static ParadaColectivo parada114Mercedes = new ParadaColectivo(114, posicionParada114Mercedes, "114", direccionParada114Mercedes,
			etiquetasParada114);

	private static ParadaColectivo parada114MercedesConPosicionDeParada114Segurola = new ParadaColectivo(114, posicionParada114Segurola, "114", direccionParada114Mercedes, etiquetasParada114);

	private static Posicion posicionParada132RosarioyLaPlata = new Posicion(-34.618422, -58.429014);

	private static Direccion direccionParada132RosarioyLaPlata = new Direccion("Rosario", 36,"Av La Plata", "Senillosa", null, null, 1424, "CABA", "Caballito", "Buenos Aires","Argentina");

	private static List<String> etiquetasParada132RosarioyLaPlata = new ArrayList<String>() {
		{
			add("parada");
			add("132");
			add("colectivo");
			add("rosario");
			add("la plata");
		}
	};;

	private static ParadaColectivo parada132RosarioyLaPlata = new ParadaColectivo(132, posicionParada132RosarioyLaPlata , "Parada 132 Rosario y La Plata", direccionParada132RosarioyLaPlata , etiquetasParada132RosarioyLaPlata );

	private static List<String> etiquetasParada114ConMasEtiquetas = new ArrayList<String>(){{
		
	addAll(etiquetasParada114);
	add("Segurola");
	add("Sube");
	}};

	private static ParadaColectivo parada114SegurolaConMasEtiquetas = new ParadaColectivo(114,posicionParada114Segurola,"114",direccionParada114Segurola,etiquetasParada114ConMasEtiquetas);

	//----------------------------------------------------------
	public static DateTime dameUnHorarioCualquiera() {
		return unHorarioCualquiera;
	}

	public static ParadaColectivo dameUnaParadaValida() {		
		return new ParadaColectivo(114, posicionParada114Segurola, "114", direccionParada114Segurola,
				etiquetasParada114);
	}
	
	public static ParadaColectivo dameOtraParadaValida() {
		return parada114Mercedes;
	}

	public static Posicion dameUnaPosicionLejanaParadaValida() {		
		return posicionLejanaParada114Segurola ;
	}
	
	public static Posicion dameUnaPosicionCercanaParadaValida() {
		return posicionCercanaParada114Segurola ;
	}
	
	public static ParadaColectivo dameUnaParada114Valida() {		
		return new ParadaColectivo(114, posicionParada114Segurola, "114", new Direccion("Av. Segurola", 230, "Bacacay", "Bogota", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina"),
				etiquetasParada114);
	}
	
	public static ParadaColectivo dameOtraParada114Valida() {		
		return parada114Mercedes;
	}

	public static ParadaColectivo dameOtraParadaConIgualPosicionQueParadaValida() {
		
		return parada114MercedesConPosicionDeParada114Segurola ;
	}

	public static ParadaColectivo dameUnaTercerParadaValida() {
		
		return parada132RosarioyLaPlata ;
	}

	public static ParadaColectivo dameUnaParadaValidaConMasEtiquetas() {
		return parada114SegurolaConMasEtiquetas ;
	}
	
}

