package POI;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class CGPTest {

	//cambiar nombres de variables en el fixture para que sean mas expresivos y representativos del dominio
	//por ejemplo: no me interesa saber si es el cgpComuna10
	
	private CGP cgp;	

	private DateTime fechaValida; //este puede ser horarioValido
	private DateTime fechaNoValida; //horarioNoDisponible?
	

	private Point posicionNoCercana; //posicionFueraDeComuna y viceversa
	private Point posicionCercana;

	@Before
	public void init() {
		
		cgp = FixtureCGP.dameCGPValido();
		fechaValida = FixtureCGP.dameHorarioValido();
		fechaNoValida = FixtureCGP.dameHorarioNoValido();		
		posicionNoCercana = FixtureCGP.damePosicionNoCercana();
		posicionCercana = FixtureCGP.damePosicionCercana();

	}
	
	
	@Test
	public void CGPestaDisponibleEnFechaValida() {
		Assert.assertTrue(cgp.estaDisponible(fechaValida));
	}
	
	@Test
	public void CGPnoEstaDisponibleEnFechaNoValida() {
		Assert.assertFalse(cgp.estaDisponible(fechaNoValida));
	}

	@Test
	public void ServicioInexistenteNoEstaDisponible(){
		Assert.assertFalse(cgp.estaDisponibleServicio("Servicio Inexistente", fechaValida));

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
		Assert.assertTrue(cgp.estaDisponibleServicio("Ecobici", fechaValida));
	}
	
	@Test
	public void servicioNoEstaDisponibleEnHorarioNoValido() {
		Assert.assertFalse(cgp.estaDisponibleServicio("Rentas", fechaNoValida));
	}
}
