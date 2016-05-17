package tpaPOIs;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.uqbar.geodds.Point;

import tpaPOIs.Comercio;
import tpaPOIs.Direccion;
import tpaPOIs.FranjaHoraria;
import tpaPOIs.Rubro;

public class FixtureComercio {
	
	static DateTime horarioAbiertoElHalcon= new DateTime(2016, 4, 19, 10, 0, 0);
	static DateTime horarioCerradoElHalcon = new DateTime(2016, 4, 19, 1, 0, 0);
	
	static Point posicionElHalcon = new Point(-34.6327106, -58.4877209);
	static Point posicionCercanaElHalcon = new Point(-34.6327105, -58.4877208);
	static Point posicionNoCercanaElHalcon = new Point(-10.6327105, -10.4877208);
	
	static Direccion direccionElHalcon = new Direccion("Av. Rivadavia", 8451, "Mercedes", "Av. Segurola", null, null, 1407, "CABA",
			"Floresta", "CABA", "Argentina");
	static List<FranjaHoraria> horariosElHalcon = new ArrayList<FranjaHoraria>() {
		{
			add(new FranjaHoraria(1, new LocalTime(9, 30), new LocalTime(13, 0)));
			add(new FranjaHoraria(1, new LocalTime(14, 30), new LocalTime(19, 0)));
			add(new FranjaHoraria(2, new LocalTime(9, 30), new LocalTime(13, 0)));
			add(new FranjaHoraria(2, new LocalTime(14, 30), new LocalTime(19, 0)));
			add(new FranjaHoraria(3, new LocalTime(9, 30), new LocalTime(13, 0)));
			add(new FranjaHoraria(3, new LocalTime(14, 30), new LocalTime(19, 0)));
			add(new FranjaHoraria(4, new LocalTime(9, 30), new LocalTime(13, 0)));
			add(new FranjaHoraria(4, new LocalTime(14, 30), new LocalTime(19, 0)));
			add(new FranjaHoraria(5, new LocalTime(9, 30), new LocalTime(13, 0)));
			add(new FranjaHoraria(5, new LocalTime(14, 30), new LocalTime(19, 0)));
			add(new FranjaHoraria(6, new LocalTime(10, 0), new LocalTime(14, 0)));
		}
	};
	static List<String> etiquetasElHalcon = new ArrayList<String>() {
		{
			add("almuerzo");
			add("cena");
			add("comida");
			add("el");
			add("halcon");
			add("efectivo");
			add("tarjeta");
			add("credito");
			add("debito");
		}
	};
	
	static Rubro restaurant = new Rubro("restaurant", 0.9);
	
	static Comercio elHalcon = new Comercio(restaurant, horariosElHalcon, posicionElHalcon, "El Halcon", direccionElHalcon,
			etiquetasElHalcon);

	public static Comercio dameComercioValido() {		
		return elHalcon;		
	}

	public static DateTime dameHorarioAbiertoComercioValido() {		
		return horarioAbiertoElHalcon;	
	}
	
	public static DateTime dameHorarioCerradoComercioValido() {		
		return horarioCerradoElHalcon;
	}
	
	public static Point damePosicionCercanaComercioValido() {		
		return posicionCercanaElHalcon;
	}
	
	public static Point damePosicionNoCercanaComercioValido() {		
		return posicionNoCercanaElHalcon;
	}
	
}
	
	
