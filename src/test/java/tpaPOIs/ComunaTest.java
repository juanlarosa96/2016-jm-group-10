package tpaPOIs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import tpaPOIs.Comuna;

public class ComunaTest {	

	private Comuna comunaValida;
	private Point posicionIncluidaEnComunaValida;
	private Point posicionNoIncluidaEnComunaValida;

	@Before
	public void init() {
		comunaValida = FixtureComuna.dameComunaValida();
		posicionIncluidaEnComunaValida=FixtureComuna.damePosicionIncluidaComunaValida();
		posicionNoIncluidaEnComunaValida=FixtureComuna.damePosicionNoIncluidaComunaValida();

	}

	@Test
	public void SiLePreguntoAUnaComunaSiIncluyeAUnaPosicionQueEstaIncluidaEnEllaRespondeTrue() {
		Assert.assertTrue(comunaValida.incluyeA(posicionIncluidaEnComunaValida));
	}

	@Test
	public void SiLePreguntoAUnaComunaSiIncluyeAUnaPosicionQueNoEstaIncluidaEnEllaRespondeFalse() {
		Assert.assertFalse(comunaValida.incluyeA(posicionNoIncluidaEnComunaValida));
	}

}
