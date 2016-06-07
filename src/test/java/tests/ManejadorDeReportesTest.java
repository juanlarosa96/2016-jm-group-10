package tests;

import java.util.HashMap;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpaPOIs.Busqueda;
import tpaPOIs.ManejadorDeFechas;
import tpaPOIs.ManejadorDeReportes;

public class ManejadorDeReportesTest {
	
	private Busqueda busqueda1;
	private Busqueda busqueda2;
	private DateTime fecha1;
	
	private ManejadorDeReportes manejadorDeReportes;

	@Before
	public void init() {
		fecha1= new DateTime(2016, 06 , 07, 20, 51);
		
		busqueda1= new Busqueda("terminalAbasto", 3, fecha1, 10.0, "hospital");
		busqueda2= new Busqueda("terminalRecoleta", 5, fecha1, 12.5, "libreria");
		
		manejadorDeReportes= ManejadorDeReportes.getInstance();
	}

	@Test
	public void siNotificoDosBusquedasConIgualFechayGeneroReporteDeBusquedasPorFechaLasDevuelveCorrectamente() {
		manejadorDeReportes.notificarBusqueda(busqueda1);
		manejadorDeReportes.notificarBusqueda(busqueda2);
		
		HashMap<String,Integer> reporte= manejadorDeReportes.generarReporteBusquedasPorFecha();
		
		Assert.assertEquals(2,reporte.get(ManejadorDeFechas.convertirFechaAString(fecha1)),0);
	}
	/*
	@Test
	public void siNotificoDosBusquedasConDistintaFechayGeneroReporteDeBusquedasPorFechaLasDevuelveCorrectamente() {
		manejadorDeReportes.notificarBusqueda(busqueda1);
		manejadorDeReportes.notificarBusqueda(busqueda3);
		
		HashMap<String,Integer> reporte= manejadorDeReportes.generarReporteBusquedasPorFecha();
		
		Assert.assertEquals(1,reporte.get(ManejadorDeFechas.convertirFechaAString(fecha1)),0);
		Assert.assertEquals(1,reporte.get(ManejadorDeFechas.convertirFechaAString(fecha2)),0);
	}
	*/

}
