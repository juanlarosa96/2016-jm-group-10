package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pois.Posicion;

import fixtures.FixtureComuna;
import pois.Comuna;

public class ComunaTest {

	private Comuna comunaValida;
	private Posicion posicionIncluidaEnComunaValida;
	private Posicion posicionNoIncluidaEnComunaValida;
	private Posicion posicionFronteraComunaValida;

	@Before
	public void init() {
		comunaValida = FixtureComuna.dameComunaValida();
		posicionIncluidaEnComunaValida = FixtureComuna.damePosicionIncluidaComunaValida();
		posicionNoIncluidaEnComunaValida = FixtureComuna.damePosicionNoIncluidaComunaValida();
		posicionFronteraComunaValida = FixtureComuna.damePosicionFronteraComunaValida();
	}

	@Test
	public void SiLePreguntoAUnaComunaSiIncluyeAUnaPosicionQueEstaIncluidaEnEllaRespondeTrue() {
		Assert.assertTrue(comunaValida.incluyeA(posicionIncluidaEnComunaValida));
	}

	@Test
	public void SiLePreguntoAUnaComunaSiIncluyeAUnaPosicionQueNoEstaIncluidaEnEllaRespondeFalse() {
		Assert.assertFalse(comunaValida.incluyeA(posicionNoIncluidaEnComunaValida));
	}

	@Test
	public void SiLePreguntoAUnaComunaSiIncluyeAUnaPosicionQueEstaEnLaFronteraDeSuTerritorioRespondeFalse() {
		Assert.assertFalse(comunaValida.incluyeA(posicionFronteraComunaValida));
	}
}
