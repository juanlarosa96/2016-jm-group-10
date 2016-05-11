package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class FixtureBanco {
	
	private static Direccion direccionBancoValido = new Direccion("Av. Rivadavia", 8468, "Benedetti", "Mariano Acosta", null, null, 1407,
			"CABA", "Floresta", "CABA", "Argentina");
	private static Point posicionBancoValido = new Point(-34.6327475, -58.4851585);
	private static List<String> etiquetasBancoValido = new ArrayList<String>() {
		{
			add("banco");
			add("provincia");
			add("depositos");
			add("extracciones");
			add("cajero");
			add("tarjeta");
			add("credito");
			add("debito");
		}
	};
	
	private static Point posicionLejana = new Point(-34.6184994, -58.4368164);
	private static Point posicionCercana = new Point(-34.6327474, -58.4851584);
	
	private static DateTime horarioBancario = new DateTime(2016, 4, 4, 10, 0);
	private static DateTime horarioNoBancario = new DateTime(2016, 4, 5, 2, 30);
	private static DateTime horarioCierreBanco = new DateTime(2016, 5, 20, 15, 0, 0);
	
	public static Banco dameUnBancoValido() {
		return new Banco(posicionBancoValido, "Banco Provincia", direccionBancoValido, etiquetasBancoValido);
	}
	
	public static Point getPosicionCercana() {
		return posicionCercana;
	}

	public static Point getPosicionLejana() {
		return posicionLejana;
	}

	public static DateTime getHorarioBancario() {
		return horarioBancario;
	}

	public static DateTime getHorarioNoBancario() {
		return horarioNoBancario;
	}

	public static DateTime getHorarioCierreBanco() {
		return horarioCierreBanco;
	}
	
	
}
