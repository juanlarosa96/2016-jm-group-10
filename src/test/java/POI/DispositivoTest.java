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
	private Servicio ecobici;

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
	private List<String> etiquetasCGP;
	private List<Point> limitesComuna10;

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
		
		bancoProvincia = new Banco(posicionBancoProvincia, "Banco Provincia", direccionBancoProvincia,
				etiquetasBancoProvincia);
		etiquetasBancoProvincia = new ArrayList<String>() {
			{
				add("banco");
				add("credicoop");
				add("depositos");
				add("extracciones");
				add("cajero");
			}
		};

		cgpComuna10 = new CGP(serviciosCGP, comuna10, posicionCgpComuna10, "CGP Comuna 10", direccionCgpComuna10, etiquetasCGP);
		serviciosCGP = new ArrayList<Servicio>() {
			{
				add(rentas);
				add(asesoramientoJuridico);
				add(ecobici);
			}
		};
		etiquetasCGP = new ArrayList<String>() {
			{
				add("CGP");
				add("Cgp");
				add("cgp");
				add("rentas");
				add("asesoramiento");
				add("juridico");
				add("ecobici");
			}
		};
	
		comuna10 = new Comuna(10, limitesComuna10);
		limitesComuna10 = new ArrayList<Point>() {
			{
				add(new Point(-34.611015, -58.529025));
				add(new Point(-34.615256, -58.531221));
				add(new Point(-34.634217, -58.529806));
				add(new Point(-34.634495, -58.510883));
				add(new Point(-34.639959, -58.509675));
				add(new Point(-34.645743, -58.502325));
				add(new Point(-34.643387, -58.497065));
				add(new Point(-34.644764, -58.495284));
				add(new Point(-34.636925, -58.478568));
				add(new Point(-34.638797, -58.476058));
				add(new Point(-34.636537, -58.471423));
				add(new Point(-34.622482, -58.477860));
				add(new Point(-34.624637, -58.482710));
				add(new Point(-34.614536, -58.495627));
				add(new Point(-34.609238, -58.500219));
				add(new Point(-34.616726, -58.513008));
				add(new Point(-34.620399, -58.516870));
			}
		};
	}

}
