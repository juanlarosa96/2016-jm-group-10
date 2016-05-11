package POI;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class ComercioTest {
	
	private Comercio comercioValido;
	private DateTime horarioAbierto;
	private DateTime horarioCerrado;
	private Point posicionCercana;
	private Point posicionNoCercana;

	@Before
	public void init() {
		comercioValido= FixtureComercio.dameComercioValido();
		horarioAbierto= FixtureComercio.dameHorarioAbierto();
		horarioCerrado= FixtureComercio.dameHorarioCerrado();
		posicionCercana= FixtureComercio.damePosicionCercana();
		posicionNoCercana= FixtureComercio.damePosicionNoCercana();				
	}

	@Test
	public void ComercioValidoEstaDisponibleEnHorarioAbierto() {
		Assert.assertTrue(comercioValido.estaDisponible(horarioAbierto));
	}

	@Test
	public void ComercioValidoNoEstaDisponibleEnHorarioCerrado() {
		Assert.assertFalse(comercioValido.estaDisponible(horarioCerrado));
	}

	@Test
	public void ComercioValidoEstaCercaDeUnaPosicionCercana() {
		Assert.assertTrue(comercioValido.estasCerca(posicionCercana));
	}
	
	@Test
	public void ComercioValidoNoEstaCercaDeUnaPosicionNoCercana() {
		Assert.assertFalse(comercioValido.estasCerca(posicionNoCercana));
	}

}
