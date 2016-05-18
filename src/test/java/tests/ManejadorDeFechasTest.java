package tests;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpaPOIs.FranjaHoraria;
import tpaPOIs.ManejadorDeFechas;

public class ManejadorDeFechasTest {
	
	private FranjaHoraria franjaHoraria;
	private DateTime fechaIncluida;
	private DateTime fechaCierre;
	private DateTime fechaNoIncluida;
	private DateTime fechaApertura;
	
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;

	@Before
	public void init(){
		horarioApertura = new LocalTime(9, 00);
		horarioCierre= new LocalTime(15, 00);
		franjaHoraria = new FranjaHoraria(1, horarioApertura, horarioCierre);
		
		fechaIncluida = new DateTime(2016,5,16,14,00);
		fechaNoIncluida = new DateTime(2016,5,16,18,00);
		fechaApertura= new DateTime(2016,5,16,9,00);
		fechaCierre= new DateTime(2016,5,16,15,00);
	}
	
	@Test
	public void siLePreguntoAlManejadorDeFechasSiUnaFechaIncluidaEnUnaFranjaHorariaEstaIncluidaRespondeTrue(){
		Assert.assertTrue(ManejadorDeFechas.estaEnFranjaHoraria(fechaIncluida, franjaHoraria));
	}
	
	@Test
	public void siLePreguntoAlManejadorDeFechasSiUnaFechaNoIncluidaEnUnaFranjaHorariaEstaIncluidaRespondeFalse(){
		Assert.assertFalse(ManejadorDeFechas.estaEnFranjaHoraria(fechaNoIncluida, franjaHoraria));
	}
	
	@Test
	public void siLePreguntoAlManejadorDeFechasSiUnaFechaDeAperturaDeUnaFranjaHorariaEstaIncluidaRespondeTrue(){
		Assert.assertTrue(ManejadorDeFechas.estaEnFranjaHoraria(fechaApertura, franjaHoraria));
	}
	
	@Test
	public void siLePreguntoAlManejadorDeFechasSiUnaFechaDeCierreDeUnaFranjaHorariaEstaIncluidaRespondeFalse(){
		Assert.assertFalse(ManejadorDeFechas.estaEnFranjaHoraria(fechaCierre, franjaHoraria));
	}
	

}
