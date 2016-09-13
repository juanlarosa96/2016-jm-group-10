package tests;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pois.Posicion;
import fixtures.FixtureBanco;
import pois.Banco;

public class BancoTest {

	private DateTime horarioBancario;
	private DateTime horarioCierreBanco;
	private DateTime horarioNoBancario;
	private DateTime horarioAperturaBanco;

	private Banco bancoValido;

	private Posicion posicionCercanaBancoValido;
	private Posicion posicionLejanaBancoValido;

	@Before
	public void init() {
		horarioBancario = FixtureBanco.getHorarioBancario();
		horarioNoBancario = FixtureBanco.getHorarioNoBancario();
		horarioCierreBanco = FixtureBanco.getHorarioCierreBanco();
		horarioAperturaBanco = FixtureBanco.getHorarioAperturaBanco();

		bancoValido = FixtureBanco.dameUnBancoValido();

		posicionCercanaBancoValido = FixtureBanco.getPosicionCercanaABancoValido();
		posicionLejanaBancoValido = FixtureBanco.getPosicionLejanaABancoValido();
	}

	@Test
	public void SiLePreguntoAUnBancoSiEstaDisponibleEnUnHorarioNoBancarioRespondeFalse() {
		Assert.assertFalse(bancoValido.estaDisponible(horarioNoBancario));
	}

	@Test
	public void SiLePreguntoAUnBancoSiEstaDisponibleEnUnHorarioBancarioRespondeTrue() {
		Assert.assertTrue(bancoValido.estaDisponible(horarioBancario));
	}

	@Test
	public void SiLePreguntoAUnBancoSiEstaDisponibleEnHorarioDeCierreRespondeFalse() {
		Assert.assertFalse(bancoValido.estaDisponible(horarioCierreBanco));
	}
	
	@Test
	public void SiLePreguntoAUnBancoSiEstaDisponibleEnHorarioDeAperturaRespondeTrue() {
		Assert.assertTrue(bancoValido.estaDisponible(horarioAperturaBanco));
	}

	@Test
	public void SiLePreguntoAUnBancoSiEstaCercaDeUnaPosicionCercanaAElRespondeTrue() {
		Assert.assertTrue(bancoValido.estasCerca(posicionCercanaBancoValido));
	}

	@Test
	public void SiLePreguntoAUnBancoSiEstaCercaDeUnaPosicionLejanaAElRespondeFalse() {
		Assert.assertFalse(bancoValido.estasCerca(posicionLejanaBancoValido));
	}

}
