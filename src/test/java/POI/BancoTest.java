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
	private DateTime horarioCierreBanco;
	private DateTime horarioNoBancario;

	private Banco bancoValido;

	private Point posicionCercana;
	private Point posicionLejana;

	@Before
	public void init() {
		FixtureBanco fixtureBanco = new FixtureBanco();
		
		horarioBancario = fixtureBanco.getHorarioBancario();
		horarioNoBancario = fixtureBanco.getHorarioNoBancario();
		horarioCierreBanco = fixtureBanco.getHorarioCierreBanco();

		bancoValido = fixtureBanco.dameUnBancoValido();

		posicionCercana = fixtureBanco.getPosicionCercana();
		posicionLejana = fixtureBanco.getPosicionLejana();
	}

	@Test
	public void unBancoValidoNoEstaDisponibleEnUnHorarioNoBancario() {
		Assert.assertFalse(bancoValido.estaDisponible(horarioNoBancario));
	}
	
	@Test
	public void unBancoValidoEstaDisponibleEnHorarioBancario() {
		Assert.assertTrue(bancoValido.estaDisponible(horarioBancario));
	}		
	
	@Test
	public void unBancoValidoNoEstaDisponibleEnHorarioDeCierre() {
		Assert.assertFalse(bancoValido.estaDisponible(horarioCierreBanco));
	}

	@Test
	public void unBancoValidoEstaCercaDeUnaPosicionCercana() {
		Assert.assertTrue(bancoValido.estasCerca(posicionCercana));
	}

	@Test
	public void unBancoValidoNoEstaCercaDeUnaPosicionLejana() {
		Assert.assertFalse(bancoValido.estasCerca(posicionLejana));
	}

}
