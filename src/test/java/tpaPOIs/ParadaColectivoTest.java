package tpaPOIs;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import tpaPOIs.Banco;
import tpaPOIs.ParadaColectivo;

public class ParadaColectivoTest {
	DateTime unHorarioCualquiera;
	Banco bancoValido;
	ParadaColectivo paradaValida;
	Point posicionLejanaParadaValida;
	Point posicionCercanaParadaValida;
	
	@Before
	public void init() {
		
		unHorarioCualquiera=FixtureParadaColectivo.dameUnHorarioCualquiera();
		paradaValida=FixtureParadaColectivo.dameUnaParadaValida();
		posicionLejanaParadaValida = FixtureParadaColectivo.dameUnaPosicionLejanaParadaValida();
		posicionCercanaParadaValida = FixtureParadaColectivo.dameUnaPosicionCercanaParadaValida();
	}
	

	@Test
	public void SiPreguntoSiUnaParadaEstaDisponibleEnUnHorarioCualquieraRespondeTrue() {
		Assert.assertTrue(paradaValida.estaDisponible(unHorarioCualquiera));
	}

	@Test
	public void SiPreguntoSiUnaParadaEstaCercaDeUnaPosicionLejanaAEllaRespondeFalse() {
		Assert.assertFalse(paradaValida.estasCerca(posicionLejanaParadaValida));
	}
	@Test
	public void SiPreguntoSiUnaParadaEstaCercaDeUnaPosicionCercanaAEllaRespondeTrue(){
		Assert.assertTrue(paradaValida.estasCerca(posicionCercanaParadaValida));
	}

    
}
