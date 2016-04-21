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

	private Banco bancoCredicoop;
	private Point posicionCredicoop;
	private Direccion direccionCredicoop;
	private List<String> etiquetasCredicoop;

	private Point posicion1;
	private Point posicion2;

	@Before
	public void init() {

		lunes4abril10am = new DateTime(2016, 4, 4, 10, 0);
		martes5abril2am = new DateTime(2016, 4, 5, 2, 30);
		jueves20mayo3pm = new DateTime(2016, 5, 20, 15, 00, 0);

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

		bancoProvincia = new Banco(posicionBancoProvincia, "Banco Provincia", direccionBancoProvincia,
				etiquetasBancoProvincia);

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

		posicion1 = new Point(-34.6184994, -58.4368164);
		posicion2 = new Point(-34.6184929, -58.4297692);

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
	public void bancoCredicoopEstaCercaDePosicion2() {
		Assert.assertTrue(bancoCredicoop.estasCerca(posicion2));
	}

	@Test
	public void bancoCredicoopNoEstaCercaDePosicion1() {
		Assert.assertFalse(bancoCredicoop.estasCerca(posicion1));
	}

}
