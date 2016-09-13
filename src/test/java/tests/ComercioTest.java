package tests;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pois.Posicion;

import fixtures.FixtureComercio;
import pois.Comercio;

public class ComercioTest {
	
	private Comercio comercioValido;
	private DateTime horarioAbiertoDeComercioValido;
	private DateTime horarioCerradoDeComercioValido;
	private Posicion posicionCercanaComercioValido;
	private Posicion posicionNoCercanaComercioValido;

	@Before
	public void init() {
		comercioValido= FixtureComercio.dameComercioValido();
		horarioAbiertoDeComercioValido= FixtureComercio.dameHorarioAbiertoComercioValido();
		horarioCerradoDeComercioValido= FixtureComercio.dameHorarioCerradoComercioValido();
		posicionCercanaComercioValido= FixtureComercio.damePosicionCercanaComercioValido();
		posicionNoCercanaComercioValido= FixtureComercio.damePosicionNoCercanaComercioValido();				
	}

	@Test
	public void SiLePreguntoAUnComercioSiEstaDisponibleEnUnHorarioEnQueEstaAbiertoRespondeTrue() {
		Assert.assertTrue(comercioValido.estaDisponible(horarioAbiertoDeComercioValido));
	}

	@Test
	public void SiLePreguntoAUnComercioSiEstaDisponibleEnUnHorarioEnQueEstaCerradoRespondeFalse() {
		Assert.assertFalse(comercioValido.estaDisponible(horarioCerradoDeComercioValido));
	}

	@Test
	public void SiLePreguntoAUnComercioSiEstaCercaDeUnaPosicionCercanaAElRespondeTrue() {
		Assert.assertTrue(comercioValido.estasCerca(posicionCercanaComercioValido));
	}
	
	@Test
	public void SiLePreguntoAUnComercioSiEstaCercaDeUnaPosicionNoCercanaAElRespondeFalse() {
		Assert.assertFalse(comercioValido.estasCerca(posicionNoCercanaComercioValido));
	}

}
