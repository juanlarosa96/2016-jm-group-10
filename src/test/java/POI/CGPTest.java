package POI;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

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
	
	
	//estos nombres de test estan bastante bien, los 2 de disponibilidad
	@Test
	public void preguntoACGPSiEstaDisponibleYdiceQueSi() {
		Assert.assertTrue(cgp.estaDisponible(fechaValida));
	}
		
	//nombre: si esta disponible y le paso un horario que deberia estar disponible
	
	//identificar las clases de equivalencia asociadas al feature
	//casos borde
	//testear solo UNA vez cada clase de equivalencia: feature bien cubierto por tests

	
	@Test
	public void preguntoACGPSiEstaDisponibleYdiceQueNo() {
		Assert.assertFalse(cgp.estaDisponible(fechaNoValida));
	}

	@Test
	public void preguntoSiTieneServicioDisponibleYEsteNoExite() {
		Assert.assertFalse(cgp.estaDisponibleServicio("Servicio Inexistente", fechaNoValida));

	}

	@Test
	public void preguntoSiEscaCercaDePosicionYDicQueNo() {
		Assert.assertFalse(cgp.estasCerca(posicionNoCercana));
	}

	@Test
	public void preguntoSiEscaCercaDePosicionYDicQueSi() {
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
