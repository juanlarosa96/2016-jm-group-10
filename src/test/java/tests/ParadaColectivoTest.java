package tests;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import fixtures.FixtureParadaColectivo;
import tpaPOIs.ParadaColectivo;

public class ParadaColectivoTest {
	private DateTime unHorarioCualquiera;
	private ParadaColectivo paradaValida;
	private ParadaColectivo otraParadaValida;
	private ParadaColectivo otraParadaConIgualPosicionDeParadaValida;
	private Point posicionLejanaParadaValida;
	private Point posicionCercanaParadaValida;
	
	@Before
	public void init() {		
		unHorarioCualquiera=FixtureParadaColectivo.dameUnHorarioCualquiera();
		paradaValida=FixtureParadaColectivo.dameUnaParadaValida();
		posicionLejanaParadaValida = FixtureParadaColectivo.dameUnaPosicionLejanaParadaValida();
		posicionCercanaParadaValida = FixtureParadaColectivo.dameUnaPosicionCercanaParadaValida();
		otraParadaValida = FixtureParadaColectivo.dameOtraParadaValida();
		otraParadaConIgualPosicionDeParadaValida = FixtureParadaColectivo.dameOtraParadaConIgualPosicionQueParadaValida();
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
	
	@Test
	public void SiPreguntoSiUnaParadaEsIgualAOtraParadaConDistintaPosicionRespondeFalse(){
		Assert.assertFalse(paradaValida.esIgualA(otraParadaValida));
	}
	
	@Test
	public void SiPreguntoSiUnaParadaEsIgualAOtraParadaConIgualPosicionRespondeTrue(){
		Assert.assertTrue(paradaValida.esIgualA(otraParadaConIgualPosicionDeParadaValida));
	}
	

    
}
