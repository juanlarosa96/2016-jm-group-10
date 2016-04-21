package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class BancoTest {

	private DateTime lunes4abril10am;
	private DateTime jueves20mayo3pm;
	private DateTime martes5abril2am;

	private Banco bancoProvincia;
	private Direccion direccionBancoProvincia;
	private Point posicionBancoProvincia;
	private List<String> etiquetasBancoProvincia;

	private ParadaColectivo parada132;
	private List<String> etiquetas132;
	private Point posicion132;
	private Direccion direccion132;

	private Banco bancoCredicoop;
	private Point posicionCredicoop;
	private Direccion direccionCredicoop;
	private List<String> etiquetasCredicoop;

	private Comercio macowins;
	private Rubro localDeRopa;
	private Point posicionMacowins;
	private Direccion direccionMacowins;
	private List<String> etiquetasMacowins;

	@Before
	public void init() {

		lunes4abril10am = new DateTime(2016, 4, 4, 10, 0);
		martes5abril2am = new DateTime(2016, 4, 5, 2, 30);

		posicionBancoProvincia = new Point(-34.6327475, -58.4851585);
		direccionBancoProvincia = new Direccion("Av. Rivadavia", 8468, "Benedetti", "Mariano Acosta", null, null, 1407,
				"CABA", "Floresta", "CABA", "Argentina");
		etiquetasBancoProvincia = new ArrayList<String>() {
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

		etiquetas132 = new ArrayList<String>() {
			{
				add("parada");
				add("colectivo");
				add("132");
			}
		};
		posicion132 = new Point(-34.6184929, -58.4297692);
		direccion132 = new Direccion("Rosario", 50, "Av La Plata", "Senillosa", null, null, 1424, "CABA", "Caballito",
				"Buenos Aires", "Argentina");
		parada132 = new ParadaColectivo(132, posicion132, "Parada 132", direccion132, etiquetas132);

		etiquetasCredicoop = new ArrayList<String>() {
			{
				add("banco");
				add("credicoop");
				add("depositos");
				add("extracciones");
				add("cajero");
			}
		};
		posicionCredicoop = new Point(-34.6156067, -58.4299701);
		direccionCredicoop = new Direccion("Av Rivadavia", 4600, "Senillosa", "Av La Plata", null, null, 1424, "CABA",
				"Caballito", "Buenos Aires", "Argentina");
		bancoCredicoop = new Banco(posicionCredicoop, "Banco Credicoop", direccionCredicoop, etiquetasCredicoop);

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

		lunes4abril10am = new DateTime(2016, 4, 4, 10, 0, 0);
		jueves20mayo3pm = new DateTime(2016, 5, 20, 15, 00, 0);

		bancoProvincia = new Banco(posicionBancoProvincia, "Banco Provincia", direccionBancoProvincia,
				etiquetasBancoProvincia);

	}

	@Test
	public void bancoProvinciaEstaDisponibleAlas10am() {
		Assert.assertTrue(bancoProvincia.estaDisponible(lunes4abril10am));
	}

	@Test
	public void bancoProvinciaNoEstaDisponibleAlas2am() {
		Assert.assertFalse(bancoProvincia.estaDisponible(martes5abril2am));
	}

	@Test
	public void bancoProvinciaNoEstaDisponibleAlas3() {
		Assert.assertFalse(bancoProvincia.estaDisponible(jueves20mayo3pm));
	}

	@Test
	public void bancoCredicoopEstaCercaDeParada132() {
		Assert.assertTrue(bancoCredicoop.estasCerca(parada132.getPosicion()));
	}

	@Test
	public void bancoCredicoopNoEstaCercaDeMacowins() {
		Assert.assertFalse(bancoCredicoop.estasCerca(macowins.getPosicion()));
	}

}
