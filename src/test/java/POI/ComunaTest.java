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
		posicionIncluida=FixtureComuna.damePosicionIncluidaComunaValida();
		posicionNoIncluida=FixtureComuna.damePosicionNoIncluidaComunaValida();

	}

	@Test
	public void comunaIncluyeAUnaPosicionQueEstaIncluida() {
		Assert.assertTrue(comunaValida.incluyeA(posicionIncluida));
	}

	@Test
	public void comunaNoIncluyeAUnaPosicionQueNoEstaIncluida() {
		Assert.assertFalse(comunaValida.incluyeA(posicionNoIncluida));
	}

}
