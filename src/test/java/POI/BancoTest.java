package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class BancoTest {

	private DateTime horarioBancario;
	private DateTime horarioCierreBanco; //cambiar nombres de estas variables, ej: 3pm por horarioBordeCierre blabla
	private DateTime horarioNoBancario;

	private Banco bancoValido;


	private Banco bancoCredicoop;
	private Point posicionCredicoop;
	private Direccion direccionCredicoop;
	private List<String> etiquetasCredicoop;

	private Point posicion1;
	private Point posicion2;

	@Before
	public void init() {
		FixtureBanco fixtureBanco = new FixtureBanco();
		FixtureHorarios fixtureHorarios = new FixtureHorarios();
		
		horarioBancario = fixtureHorarios.dameUnHorarioBancario();
		horarioNoBancario = fixtureHorarios.dameUnHorarioALaHoraDeCierre();
		horarioCierreBanco = fixtureHorarios.dameUnHorarioNoBancario();

		bancoValido = fixtureBanco.dameUnBancoValido();

		posicion1 = new Point(-34.6184994, -58.4368164);
		posicion2 = new Point(-34.6184929, -58.4297692);

	}

	//lunes4abril10am: horarioBancario
	//martes5abril2am: horarioNoBancario -- asi no tengo que conocer el horario bancario
	//bancoProvincia: bancoValido

	@Test
	public void unBancoValidoNoEstaDisponibleEnUnHorarioNoBancario() {
		Assert.assertFalse(bancoValido.estaDisponible(horarioNoBancario));
	}
	
	@Test
	public void unBancoValidoEstaDisponibleEnHorarioBancario() {
		Assert.assertTrue(bancoValido.estaDisponible(horarioBancario));
	}
	
	
	//Que el nombre del test exprese todo esto...
	//test
	//hipotesis
	//conjunto de reglas
	//tesis: comprobacion de hipotesis
		
	
	@Test
	public void unBancoValidoNoEstaDisponibleEnHorarioDeCierre() {
		Assert.assertFalse(bancoValido.estaDisponible(horarioCierreBanco));
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
