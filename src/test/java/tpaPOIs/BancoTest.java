package tpaPOIs;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import tpaPOIs.Banco;

public class BancoTest {

	private DateTime horarioBancario;
	private DateTime horarioCierreBanco;
	private DateTime horarioNoBancario;

	private Banco bancoValido;

	private Point posicionCercanaBancoValido;
	private Point posicionLejanaBancoValido;

	@Before
	public void init() {
		horarioBancario = FixtureBanco.getHorarioBancario();
		horarioNoBancario = FixtureBanco.getHorarioNoBancario();
		horarioCierreBanco = FixtureBanco.getHorarioCierreBanco();

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
	public void SiLePreguntoAUnBancoSiEstaCercaDeUnaPosicionCercanaAElRespondeTrue() {
		Assert.assertTrue(bancoValido.estasCerca(posicionCercanaBancoValido));
	}

	@Test
	public void SiLePreguntoAUnBancoSiEstaCercaDeUnaPosicionLejanaAElRespondeFalse() {
		Assert.assertFalse(bancoValido.estasCerca(posicionLejanaBancoValido));
	}

}
