package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class FixtureBanco {
	
	private Direccion direccionBancoValido = new Direccion("Av. Rivadavia", 8468, "Benedetti", "Mariano Acosta", null, null, 1407,
			"CABA", "Floresta", "CABA", "Argentina");
	private Point posicionBancoValido = new Point(-34.6327475, -58.4851585);
	private List<String> etiquetasBancoValido = new ArrayList<String>() {
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
	
	private Point posicionCercana = new Point(-34.6184994, -58.4368164);
	private Point posicionLejana = new Point(-34.6184929, -58.4297692);
	
	private DateTime horarioBancario = new DateTime(2016, 4, 4, 10, 0);
	private DateTime horarioNoBancario = new DateTime(2016, 4, 5, 2, 30);
	private DateTime horarioCierreBanco = new DateTime(2016, 5, 20, 15, 00, 0);
	
	public Banco dameUnBancoValido() {
		return new Banco(posicionBancoValido, null, direccionBancoValido, etiquetasBancoValido);
	}
	
	public Point getPosicionCercana() {
		return posicionCercana;
	}

	public Point getPosicionLejana() {
		return posicionLejana;
	}

	public DateTime getHorarioBancario() {
		return horarioBancario;
	}

	public DateTime getHorarioNoBancario() {
		return horarioNoBancario;
	}

	public DateTime getHorarioCierreBanco() {
		return horarioCierreBanco;
	}
	
	
}
