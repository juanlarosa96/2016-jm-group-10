package POI;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class DispositivoTest {

	private Dispositivo dispositivo;
	private Point posicionDispositivo;
	
	private CGP cgpValido;


	private Servicio rentas;
	private Servicio ecobici;

	private Banco bancoValido;
	
	private Comercio elHalcon;
	private Direccion direccionElHalcon;
	private Point posicionElHalcon;
	private Rubro restaurant;
	private List<FranjaHoraria> horariosElHalcon;
	private List<String> etiquetasElHalcon;

	private ParadaColectivo parada114Segurola;
	private Direccion direccionParada114Segurola;
	private Point posicionParada114Segurola;
	private ParadaColectivo parada114Mercedes;
	private Direccion direccionParada114Mercedes;
	private Point posicionParada114Mercedes;
	private List<String> etiquetasParada114;

	private List<POI> listaPoisDispositivo;
	private DateTime lunes4abril10am;
	private DateTime martes5abril2am;

	private Point posicionMcDonalds;
	private Direccion direccionMcDonalds;
	private ArrayList<String> etiquetasMcDonalds;
	private Comercio mcDonalds;

	private Servicio asesoramientoLegal;
	private ArrayList<Point> limitesComuna5;
	private Direccion direccionCgpComuna5;
	private Point posicionCgpComuna5;
	private ArrayList<Servicio> serviciosCGP5;
	private ArrayList<String> etiquetasCGP5;
	private Comuna comuna5;
	private CGP cgpComuna5;

	private List<POI> CGPsConRentas;

	@Before
	public void init() {

		lunes4abril10am = new DateTime(2016, 4, 4, 10, 0);
		martes5abril2am = new DateTime(2016, 4, 5, 2, 30);

		posicionDispositivo = new Point(-34.631402, -58.488060);
		dispositivo = new Dispositivo(posicionDispositivo);

		posicionParada114Segurola = new Point(-34.631997, -58.484737);
		direccionParada114Segurola = new Direccion("Av. Segurola", 230, "Bacacay", "Bogota", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		etiquetasParada114 = new ArrayList<String>() {
			{
				add("Parada");
				add("parada");
				add("114");
				add("colectivo");
			}
		};
		parada114Segurola = new ParadaColectivo(114, posicionParada114Segurola, "114", direccionParada114Segurola,
				etiquetasParada114);

		posicionParada114Mercedes = new Point(-34.6334512, -58.4839027);
		direccionParada114Mercedes = new Direccion("Mercedes", 17, "Av. Rivadavia", "Yerbal", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");

		parada114Mercedes = new ParadaColectivo(114, posicionParada114Mercedes, "114", direccionParada114Mercedes,
				etiquetasParada114);

		bancoValido = FixtureBanco.dameUnBancoValido();

		cgpValido = FixtureCGP.dameCGPValido();

		posicionCgpComuna5 = new Point(-34.6229418, -58.4146764);
		direccionCgpComuna5 = new Direccion("Carlos Calvo", 3307, "Virrey Liniers", "Sanchez de Loria", null, null,
				1230, "CABA", "Boedo", "CABA", "Argentina");

		limitesComuna5 = new ArrayList<Point>() {
			{
				add(new Point(-34.598322, -58.412213));
				add(new Point(-34.602632, -58.432984));
				add(new Point(-34.639930, -58.423477));
				add(new Point(-34.637734, -58.411375));
			}
		};

		serviciosCGP5 = new ArrayList<Servicio>() {
			{
				add(rentas);
				add(asesoramientoLegal);
				add(ecobici);
			}
		};

		etiquetasCGP5 = new ArrayList<String>() {
			{
				add("CGP");
				add("Cgp");
				add("cgp");
				add("rentas");
				add("asesoramiento");
				add("legal");
				add("ecobici");
			}
		};

		comuna5 = new Comuna(5, limitesComuna5);

		cgpComuna5 = new CGP(serviciosCGP5, comuna5, posicionCgpComuna5, "CGP Comuna 5", direccionCgpComuna5,
				etiquetasCGP5);

		posicionElHalcon = new Point(-34.6327106, -58.4877209);
		direccionElHalcon = new Direccion("Av. Rivadavia", 8451, "Mercedes", "Av. Segurola", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		horariosElHalcon = new ArrayList<FranjaHoraria>() {
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
		etiquetasElHalcon = new ArrayList<String>() {
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

		restaurant = new Rubro("Restaurant", 0.3);
		elHalcon = new Comercio(restaurant, horariosElHalcon, posicionElHalcon, "El Halcon", direccionElHalcon,
				etiquetasElHalcon);

		posicionMcDonalds = new Point(-34.6184994, -58.4368164);
		direccionMcDonalds = new Direccion("Av. Acoyte", 88, "Av. Rivadavia", "Yerbal", null, null, 1424, "CABA",
				"Caballito", "Buenos Aires", "Argentina");
		etiquetasMcDonalds = new ArrayList<String>() {
			{
				add("comida");
				add("rapida");
				add("local");
				add("hamburguesa");
				add("tarjeta");
				add("credito");
				add("debito");
			}
		};
		mcDonalds = new Comercio(restaurant, null, posicionMcDonalds, "McDonalds Acoyte", direccionMcDonalds,
				etiquetasMcDonalds);

		listaPoisDispositivo = new ArrayList<POI>() {
			{
				add(parada114Segurola);
				add(parada114Mercedes);
				add(elHalcon);
				add(mcDonalds);
				add(bancoValido);
				add(cgpValido);
				add(cgpComuna5);
			}
		};

		Dispositivo.setListaPois(listaPoisDispositivo);

		CGPsConRentas = new ArrayList<POI>();
	}

	@Test
	public void consultoSiUnaParadaValidaSeEncuentraEnLaListaDePoisYDiceQueSi() {
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(parada114Segurola));
	}


	@Test
	public void consultoSiAlgunPOITieneEtiquetaConUnaPalabraClaveYDevuelve3() {
		Assert.assertEquals(3, (dispositivo.buscarPOIs("tarjeta de credito").size()), 0);
	}

	@Test
	public void consultoSiAlgunPoiTieneEtiquetaConUnaPalabraClaveQueNingunoTieneYNoHayNinguno() {
		Assert.assertEquals(0, (dispositivo.buscarPOIs("negra").size()), 0);
	}

	@Test
	public void consultoSiAlgunaCGTTieneEtiquetaConUnaPalabraClaveYDevuelve2() {
		Assert.assertEquals(2, (dispositivo.buscarPOIs("asesoramiento").size()), 0);
	}

	@Test
	public void consultoSiUnServicioValidoSeEncuentraDisponibleEnHorarioAbiertoEnAlgunCGPYDiceQueSeEncuentraEn2CGP() {
		CGPsConRentas = dispositivo.buscarServicioDisponible("Rentas", lunes4abril10am);
		Assert.assertEquals(2, CGPsConRentas.size(), 0);
		Assert.assertTrue(CGPsConRentas.contains(cgpValido));
		Assert.assertTrue(CGPsConRentas.contains(cgpComuna5));
	}

	@Test
	public void consultoSiUnServicioValidoEstaDisponibleEnHorarioDondeEstaCerradoYDiceQueNo() {
		CGPsConRentas = dispositivo.buscarServicioDisponible("Rentas", martes5abril2am);
		Assert.assertEquals(0, CGPsConRentas.size(), 0);

	}

}
