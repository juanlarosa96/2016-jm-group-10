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
	private Busqueda busqueda3;
	private DateTime fecha2;

	@Before
	public void init() {
		fecha1 = new DateTime(2016, 06, 07, 20, 51);
		fecha2 = new DateTime(2014, 03, 05, 10, 20);

		busqueda1 = new Busqueda("terminalAbasto", 3, fecha1, 10.0, "hospital");
		busqueda2 = new Busqueda("terminalRecoleta", 5, fecha1, 12.5, "libreria");
		busqueda3 = new Busqueda("terminalCaballito", 4, fecha2, 3.4, "restaurant");

		manejadorDeReportes = ManejadorDeReportes.getInstance();
		manejadorDeReportes.limpiarBusquedas();
	}

	@Test
	public void SiNotificoDosBusquedasConIgualFechayGeneroReporteDeBusquedasPorFechaDevuelveDosBusquedasParaEsaFecha() {
		manejadorDeReportes.notificarBusqueda(busqueda1);
		manejadorDeReportes.notificarBusqueda(busqueda2);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteBusquedasPorFecha();

		Assert.assertEquals(2, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha1)), 0);
	}

	@Test
	public void SiNotificoDosBusquedasConDistintaFechayGeneroReporteDeBusquedasPorFechaDevuelve1BusquedaParaCadaFecha() {
		manejadorDeReportes.notificarBusqueda(busqueda1);
		manejadorDeReportes.notificarBusqueda(busqueda3);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteBusquedasPorFecha();

		Assert.assertEquals(1, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha1)), 0);
		Assert.assertEquals(1, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha2)), 0);
	}

	@Test
	public void SiNotificoDosBusquedasConIgualFechaYOtraConOtraFechaYGeneroReporteDeBusquedasPorFechaDevuelveLaCantidadDeBusquedasCorrectaParaCadaFecha() {
		manejadorDeReportes.notificarBusqueda(busqueda1);
		manejadorDeReportes.notificarBusqueda(busqueda2);
		manejadorDeReportes.notificarBusqueda(busqueda3);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteBusquedasPorFecha();
		
		Assert.assertEquals(2, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha1)), 0);
		Assert.assertEquals(1, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha2)), 0);
	}

	@Test
	public void SiNoNotificoNingunaBusquedaMeGeneraUnReporteVacio() {
		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteBusquedasPorFecha();

		Assert.assertTrue(reporte.isEmpty());
	}
}
