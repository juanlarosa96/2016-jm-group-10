package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class ParadaColectivoTest {
	DateTime unHorarioCualquiera;
	Banco bancoValido;
	ParadaColectivo paradaValida;
	
	@Before
	public void init() {
		
		unHorarioCualquiera=FixtureParadaColectivo.dameUnHorarioCualquiera();
		bancoValido=FixtureParadaColectivo.dameBancoValido();
		paradaValida=FixtureParadaColectivo.dameUnaParadaValida();

	}
	

	@Test
	public void consultoSiUnaParadaDeColectivoEstaDisponibleYDiceQueSi() {
		Assert.assertTrue(paradaValida.estaDisponible(unHorarioCualquiera));
	}

	@Test
	public void ConsultoSiParadaValidaEstaCercaDePoiValidoYDiceQueNo() {
		Assert.assertFalse(paradaValida.estasCerca(bancoValido.getPosicion()));
	}

	@Test
	public void ConsultoSiParadaValidaEstaAMasDe3CuadrasDePoiValidoYDiceQueNo() {
		Assert.assertTrue(paradaValida.distanciaAPoi(bancoValido) > 0.3);
	}

}
