package tpaPOIs;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import tpaPOIs.CGP;

public class CGPTest {
	
	private CGP cgp;	

	private DateTime horarioValido; 
	private DateTime horarioNoValido; 	

	private Point posicionNoCercana;
	private Point posicionCercana;

	@Before
	public void init() {
		
		cgp = FixtureCGP.dameCGPValido();
		horarioValido = FixtureCGP.dameHorarioValidoParaCgpValido();
		horarioNoValido = FixtureCGP.dameHorarioNoValidoParaCgpValido();		
		posicionNoCercana = FixtureCGP.damePosicionNoCercanaCgpValido();
		posicionCercana = FixtureCGP.damePosicionCercanaCgpValido();

	}
	
	
	@Test
	public void CGPestaDisponibleEnHorarioValido() {
		Assert.assertTrue(cgp.estaDisponible(horarioValido));
	}
	
	@Test
	public void CGPnoEstaDisponibleEnHorarioNoValido() {
		Assert.assertFalse(cgp.estaDisponible(horarioNoValido));
	}

	@Test
	public void ServicioInexistenteNoEstaDisponible(){
		Assert.assertFalse(cgp.estaDisponibleServicio("Servicio Inexistente", horarioValido));

	}

	@Test
	public void CGPnoEstaCercaDePosicionNoCercana() {
		Assert.assertFalse(cgp.estasCerca(posicionNoCercana));
	}

	@Test
	public void CGPestaCercaDePosicionCercana() {
		Assert.assertTrue(cgp.estasCerca(posicionCercana));
	}	

	@Test
	public void servicioEstaDisponibleEnHorarioValido() {
		Assert.assertTrue(cgp.estaDisponibleServicio("Rentas", horarioValido));
	}
	
	@Test
	public void servicioNoEstaDisponibleEnHorarioNoValido() {
		Assert.assertFalse(cgp.estaDisponibleServicio("Rentas", horarioNoValido));
	}
}
