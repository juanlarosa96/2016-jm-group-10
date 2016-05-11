package POI;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class ComunaTest {	

	private Comuna comunaValida;
	private Point posicionIncluida;
	private Point posicionNoIncluida;

	@Before
	public void init() {
		comunaValida = FixtureComuna.dameComunaValida();
		posicionIncluida=FixtureComuna.damePosicionIncluida();
		posicionNoIncluida=FixtureComuna.damePosicionNoIncluida();

	}

	@Test
	public void consultoSiUnaComunaIncluyeAUnaPosicionQueEstaInlcuidaYDiceQueSi() {
		Assert.assertTrue(comunaValida.incluyeA(posicionIncluida));
	}

	@Test
	public void consultoSiUnaComunaIncluyeAUnaPosicionQueNoEstaInlcuidaYDiceQueNo() {
		Assert.assertFalse(comunaValida.incluyeA(posicionNoIncluida));
	}

}
