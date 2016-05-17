package tpaPOIs;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import tpaPOIs.Comercio;

public class ComercioTest {
	
	private Comercio comercioValido;
	private DateTime horarioAbiertoDeComercioValido;
	private DateTime horarioCerradoDeComercioValido;
	private Point posicionCercanaComercioValido;
	private Point posicionNoCercanaComercioValido;

	@Before
	public void init() {
		comercioValido= FixtureComercio.dameComercioValido();
		horarioAbiertoDeComercioValido= FixtureComercio.dameHorarioAbiertoComercioValido();
		horarioCerradoDeComercioValido= FixtureComercio.dameHorarioCerradoComercioValido();
		posicionCercanaComercioValido= FixtureComercio.damePosicionCercanaComercioValido();
		posicionNoCercanaComercioValido= FixtureComercio.damePosicionNoCercanaComercioValido();				
	}

	@Test
	public void ComercioValidoEstaDisponibleEnHorarioAbierto() {
		Assert.assertTrue(comercioValido.estaDisponible(horarioAbiertoDeComercioValido));
	}

	@Test
	public void ComercioValidoNoEstaDisponibleEnHorarioCerrado() {
		Assert.assertFalse(comercioValido.estaDisponible(horarioCerradoDeComercioValido));
	}

	@Test
	public void ComercioValidoEstaCercaDeUnaPosicionCercana() {
		Assert.assertTrue(comercioValido.estasCerca(posicionCercanaComercioValido));
	}
	
	@Test
	public void ComercioValidoNoEstaCercaDeUnaPosicionNoCercana() {
		Assert.assertFalse(comercioValido.estasCerca(posicionNoCercanaComercioValido));
	}

}
