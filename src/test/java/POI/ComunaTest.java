package POI;

import java.util.ArrayList;
import java.util.List;

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
	public void ComunaIncluyeAUnaPosicionQueEstaInlcuida() {
		Assert.assertTrue(comunaValida.incluyeA(posicionIncluida));
	}

	@Test
	public void ComunaNoInlcuyeAUnaPosicionQueNoEstaIncluida() {
		Assert.assertFalse(comunaValida.incluyeA(posicionNoIncluida));
	}

}
