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
	private DateTime jueves20mayo3pm; //cambiar nombres de estas variables, ej: 3pm por horarioBordeCierre blabla
	private DateTime martes5abril2am;

	private Banco bancoValido;
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
		FixtureBanco fixtureBanco = new FixtureBanco();
		
		lunes4abril10am = new DateTime(2016, 4, 4, 10, 0);
		martes5abril2am = new DateTime(2016, 4, 5, 2, 30);
		jueves20mayo3pm = new DateTime(2016, 5, 20, 15, 00, 0);

		Banco bancoValido = fixtureBanco.dameUnBancoValido();

		posicion1 = new Point(-34.6184994, -58.4368164);
		posicion2 = new Point(-34.6184929, -58.4297692);

	}

	@Test
	public void bancoValidoEstaDisponibleEnHorarioBancario() {
		Assert.assertTrue(bancoValido.estaDisponible(lunes4abril10am));
	}
	
	//lunes4abril10am: horarioBancario
	//martes5abril2am: horarioNoBancario -- asi no tengo que conocer el horario bancario
	//bancoProvincia: bancoValido

	@Test
	public void unBancoValidoNoEstaDisponibleEnUnHorarioNoBancario() {
		Assert.assertFalse(bancoValido.estaDisponible(martes5abril2am));
	}
	
	//Que el nombre del test exprese todo esto...
	//test
	//hipotesis
	//conjunto de reglas
	//tesis: comprobacion de hipotesis
		
	
	@Test
	public void bancoProvinciaNoEstaDisponibleAlas3() {
		Assert.assertFalse(bancoValido.estaDisponible(jueves20mayo3pm));
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
