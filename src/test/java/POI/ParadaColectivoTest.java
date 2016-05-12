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
	Point posicionLejana;
	Point posicionCercana;
	
	@Before
	public void init() {
		
		unHorarioCualquiera=FixtureParadaColectivo.dameUnHorarioCualquiera();
		paradaValida=FixtureParadaColectivo.dameUnaParadaValida();
		posicionLejana = FixtureParadaColectivo.dameUnaPosicionLejana();
		posicionCercana = FixtureParadaColectivo.dameUnaPosicionCercana();
	}
	

	@Test
	public void paradaValidaEstaDisponibleEnCualquierHorario() {
		Assert.assertTrue(paradaValida.estaDisponible(unHorarioCualquiera));
	}

	@Test
	public void paradaValidaNoEstaCercaDeUnaPosicionLejana() {
		Assert.assertFalse(paradaValida.estasCerca(posicionLejana));
	}
	@Test
	public void paradaValidaEstasCercaDeUnaPosicionCercana(){
		Assert.assertTrue(paradaValida.estasCerca(posicionCercana));
	}

    
}
