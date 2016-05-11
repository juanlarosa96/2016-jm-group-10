package POI;

import java.util.ArrayList;
import java.util.List;
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
	
	public Banco dameUnBancoValido() {
		return new Banco(posicionBancoValido, null, direccionBancoValido, etiquetasBancoValido);
	}
}
