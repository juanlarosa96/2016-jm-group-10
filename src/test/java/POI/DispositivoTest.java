package POI;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.uqbar.geodds.Point;

public class DispositivoTest {

	private Dispositivo dispositivo;
	private CGP cgpComuna10;
	private Banco bancoProvincia;
	private Comercio elHalcon;
	private Comuna comuna10;
	private ParadaColectivo parada114;
	private Rubro restaurant;
	private Servicio rentas;
	private Servicio asesoramientoJuridico;
	private Direccion direccionCgpComuna10;
	private Direccion direccionElHalcon;
	private Direccion direccionBancoProvincia;
	private Direccion direccionParada114;
	private Point posicionDispositivo;
	private Point posicionCgpComuna10;
	private Point posicionElHalcon;
	private Point posicionBancoProvincia;
	private Point posicionParada114;
	private List<FranjaHoraria> horariosBanco;
	private List<String> etiquetasBancoProvincia;
	private List<Servicio> serviciosCGP;

	@Before
	public void init() {
		posicionDispositivo = new Point(-34.631402, -58.488060);
		posicionCgpComuna10 = new Point(-34.6369004, -58.4959096);
		posicionElHalcon = new Point(-34.6327106, -58.4877209);
		posicionBancoProvincia = new Point(-34.6327475, -58.4851585);
		posicionParada114 = new Point(-34.631997, -58.484737);
		
		direccionCgpComuna10 = new Direccion("Bacacay", 3968, "Campana", "Concordia", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		direccionElHalcon = new Direccion("Av. Rivadavia", 8451, "Mercedes", "Av. Segurola", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		direccionBancoProvincia = new Direccion("Av. Rivadavia", 8468, "Benedetti", "Mariano Acosta", null, null, 1407,
				"CABA", "Floresta", "CABA", "Argentina");
		direccionParada114 = new Direccion("Av. Segurola", 230, "Bacacay", "Bogota", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		
		dispositivo = new Dispositivo(posicionDispositivo);
		
		etiquetasBancoProvincia = new ArrayList<String>() {
			{
				add("banco");
				add("credicoop");
				add("depositos");
				add("extracciones");
				add("cajero");
			}
		};
		bancoProvincia = new Banco(posicionBancoProvincia, "Banco Provincia", direccionBancoProvincia,
				etiquetasBancoProvincia);
		
		cgpComuna10 = new CGP();
		
	}

}
