package tests;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import fixtures.FixtureCGP;
import tpaPOIs.CGP;

public class CGPTest {

	private CGP cgp;
	private CGP otroCgp;
	private CGP otroCgpConIgualDireccionQueElCgpValido;

	private DateTime horarioValido;
	private DateTime horarioNoValido;

	private Point posicionNoCercana;
	private Point posicionCercana;

	@Before
	public void init() {

		cgp = FixtureCGP.dameCGPValido();
		otroCgp = FixtureCGP.dameOtroCgpValido();
		otroCgpConIgualDireccionQueElCgpValido = FixtureCGP.dameOtroCgpConIgualDireccionQueElCGPValido();

		horarioValido = FixtureCGP.dameHorarioValidoParaCgpValido();
		horarioNoValido = FixtureCGP.dameHorarioNoValidoParaCgpValido();
		posicionNoCercana = FixtureCGP.damePosicionNoCercanaCgpValido();
		posicionCercana = FixtureCGP.damePosicionCercanaCgpValido();

	}

	@Test
	public void SiLePreguntoAUnCGPSiEstaDisponibleEnUnHorarioValidoRespondeTrue() {
		Assert.assertTrue(cgp.estaDisponible(horarioValido));
	}

	@Test
	public void SiLePreguntoAUnCGPSiEstaDisponibleEnUnHorarioNoValidoRespondeFalse() {
		Assert.assertFalse(cgp.estaDisponible(horarioNoValido));
	}

	@Test
	public void SiLePreguntoAUnCGPSiEstaCercaDeUnaPosicionNoCercanaAElRespondeFalse() {
		Assert.assertFalse(cgp.estasCerca(posicionNoCercana));
	}

	@Test
	public void SiLePreguntoAUnCGPSiEstaCercaDeUnaPosicionCercanaAElRespondeTrue() {
		Assert.assertTrue(cgp.estasCerca(posicionCercana));
	}

	@Test
	public void SiLePreguntoAUnCGPSiEstaDisponibleUnServicioEnUnHorarioValidoRespondeTrue() {
		Assert.assertTrue(cgp.estaDisponibleServicio("Rentas", horarioValido));
	}

	@Test
	public void SiLePreguntoAUnCGPSiEstaDisponibleUnServicioEnUnHorarioNoValidoRespondeFalse() {
		Assert.assertFalse(cgp.estaDisponibleServicio("Rentas", horarioNoValido));
	}

	@Test
	public void SiLePreguntoAUnCGPSiUnServicioInexistenteEstaDisponibleRespondeFalse() {
		Assert.assertFalse(cgp.estaDisponibleServicio("Servicio Inexistente", horarioValido));

	}

	@Test
	public void SiLePreguntoAUnCGPSiEsIgualAUnCgpConLaMismaDireccionRespondeTrue() {
		Assert.assertTrue(cgp.esIgualA(otroCgpConIgualDireccionQueElCgpValido));

	}

	@Test
	public void SiLePreguntoAUnCGPSiEsIgualAOtroCgpConDistintaDireccionRespondeFalse() {
		Assert.assertFalse(cgp.esIgualA(otroCgp));

	}

}
