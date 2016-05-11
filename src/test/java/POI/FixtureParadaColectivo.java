package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.uqbar.geodds.Point;

public class FixtureParadaColectivo {
	
	private static DateTime unHorarioCualquiera = new DateTime(2016, 4, 5, 2, 30);

	private static List<String>	etiquetasParadaValida = new ArrayList<String>() {
			{
				add("parada");
				add("colectivo");
				add("132");
			}
		};
	private static Point posicionParadaValida = new Point(-34.6184929, -58.4297692);
	private static Direccion direccionParadaValida = new Direccion("Rosario", 50, "Av La Plata", "Senillosa", null, null, 1424, "CABA", "Caballito",
				"Buenos Aires", "Argentina");
	private static ParadaColectivo	paradaValida = new ParadaColectivo(132, posicionParadaValida, "Parada 132", direccionParadaValida, etiquetasParadaValida);

	private static List<String> etiquetasBancoValido = new ArrayList<String>() {
			{
				add("banco");
				add("credicoop");
				add("depositos");
				add("extracciones");
				add("cajero");
			}
		};
		private static Point posicionBancoValido = new Point(-34.6156067, -58.4299701);
		private static Direccion direccionBancoValido = new Direccion("Av Rivadavia", 4600, "Senillosa", "Av La Plata", null, null, 1424, "CABA",
				"Caballito", "Buenos Aires", "Argentina");
		private static Banco bancoValido = new Banco(posicionBancoValido, "Banco Credicoop", direccionBancoValido, etiquetasBancoValido);


	public static DateTime dameUnHorarioCualquiera() {
		return unHorarioCualquiera;
	}

	public static Banco dameBancoValido() {
		return bancoValido;
	}

	public static ParadaColectivo dameUnaParadaValida() {
		
		return paradaValida;
	}
	
}
