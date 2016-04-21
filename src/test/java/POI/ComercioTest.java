package POI;

import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class ComercioTest {

	private Rubro restaurant;
	private Comercio elHalcon;
	private Direccion direccionElHalcon;
	private Point posicionElHalcon;
	private List<FranjaHoraria> horariosElHalcon;
	private List<String> etiquetasElHalcon;

	private DateTime martes19abril10am;
	private DateTime martes19abril1am;

	private Point posicion3;

	private Rubro localDeRopa;
	private Comercio macowins;
	private Point posicionMacowins;
	private Direccion direccionMacowins;
	private List<String> etiquetasMacowins;

	@Before
	public void init() {

		martes19abril10am = new DateTime(2016, 4, 19, 10, 0, 0);
		martes19abril1am = new DateTime(2016, 4, 19, 1, 0, 0);

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
		elHalcon = new Comercio(restaurant, horariosElHalcon, posicionElHalcon, "El Halcon", direccionElHalcon,
				etiquetasElHalcon);

		posicion3 = new Point(-34.6156067, -58.4299701);

		localDeRopa = new Rubro("Local de Ropa", 0.9);
		posicionMacowins = new Point(-34.6184994, -58.4368164);
		direccionMacowins = new Direccion("Av. Acoyte", 56, "Av. Rivadavia", "Yerbal", null, null, 1424, "CABA",
				"Caballito", "Buenos Aires", "Argentina");
		etiquetasMacowins = new ArrayList<String>() {
			{
				add("local");
				add("ropa");
				add("macowins");
			}
		};
		macowins = new Comercio(localDeRopa, null, posicionMacowins, "Macowins", direccionMacowins, etiquetasMacowins);
	}

	@Test
	public void estaDisponibleElHalcon() {
		Assert.assertTrue(elHalcon.estaDisponible(martes19abril10am));
	}

	@Test
	public void noEstaDisponibleElHalcon() {
		Assert.assertFalse(elHalcon.estaDisponible(martes19abril1am));
	}

	@Test
	public void MacowinsConsideraComoCerca9Cuadras() {

		Assert.assertEquals(0.9, macowins.condicionDeCercania(), 0.0);
	}

	@Test
	public void MacowinsEstaCercaDeCredicoop() {
		Assert.assertTrue(macowins.estasCerca(posicion3));
	}

}
