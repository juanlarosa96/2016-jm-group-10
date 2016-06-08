package tests;

import java.util.HashMap;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eventosBusqueda.Busqueda;
import eventosBusqueda.ManejadorDeReportes;
import herramientas.ManejadorDeFechas;

public class ManejadorDeReportesTest {

	private Busqueda busquedaAbasto1;
	private Busqueda busquedaRecoleta1;
	private DateTime fecha1;

	private ManejadorDeReportes manejadorDeReportes;
	private Busqueda busquedaCaballito1;
	private DateTime fecha2;
	private Busqueda busquedaAbasto2;

	@Before
	public void init() {
		fecha1 = new DateTime(2016, 06, 07, 20, 51);
		fecha2 = new DateTime(2014, 03, 05, 10, 20);

		busquedaAbasto1 = new Busqueda("terminalAbasto", 3, fecha1, 10.0, "hospital");
		busquedaRecoleta1 = new Busqueda("terminalRecoleta", 5, fecha1, 12.5, "libreria");
		busquedaCaballito1 = new Busqueda("terminalCaballito", 4, fecha2, 3.4, "restaurant");
		busquedaAbasto2= new Busqueda("terminalAbasto", 8 , fecha2, 2.5, "burguer");

		manejadorDeReportes = ManejadorDeReportes.getInstance();
		manejadorDeReportes.limpiarBusquedas();
	}

	@Test
	public void SiNotificoDosBusquedasConIgualFechayGeneroReporteDeBusquedasPorFechaDevuelveDosBusquedasParaEsaFecha() {
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaRecoleta1);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteBusquedasPorFecha();

		Assert.assertEquals(2, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha1)), 0);
	}

	@Test
	public void SiNotificoDosBusquedasConDistintaFechayGeneroReporteDeBusquedasPorFechaDevuelve1BusquedaParaCadaFecha() {
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaCaballito1);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteBusquedasPorFecha();

		Assert.assertEquals(1, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha1)), 0);
		Assert.assertEquals(1, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha2)), 0);
	}

	@Test
	public void SiNotificoDosBusquedasConIgualFechaYOtraConOtraFechaYGeneroReporteDeBusquedasPorFechaDevuelveLaCantidadDeBusquedasCorrectaParaCadaFecha() {
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaRecoleta1);
		manejadorDeReportes.notificarBusqueda(busquedaCaballito1);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteBusquedasPorFecha();
		
		Assert.assertEquals(2, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha1)), 0);
		Assert.assertEquals(1, reporte.get(ManejadorDeFechas.convertirFechaAString(fecha2)), 0);
	}

	@Test
	public void SiNoNotificoNingunaBusquedaMeGeneraUnReporteDeBusquedasPorFechaVacio() {
		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteBusquedasPorFecha();

		Assert.assertTrue(reporte.isEmpty());
	}
	
	@Test
	public void SiNoNotificoNingunaBusquedaMeGeneraUnReporteDeCantidadDeResultadosTotalesPorTerminalVacio(){
		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteDeResultadoTotalesPorTerminales();

		Assert.assertTrue(reporte.isEmpty());
	}
	
	@Test
	public void SiNotificoDosBusquedasConIgualTerminalYGeneroReporteDeCantidadDeResultadosTotalesPorTerminalMeDevuelveLaSumaDeResultadosDeLasDosBusquedasParaEsaTerminal(){
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaAbasto2);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteDeResultadoTotalesPorTerminales();
		
		Integer cantResultadosTotalesAbasto = busquedaAbasto1.getCantResultados()+busquedaAbasto2.getCantResultados();
		String terminalAbasto = busquedaAbasto1.getNombreTerminal();
		
		Assert.assertEquals(cantResultadosTotalesAbasto, reporte.get(terminalAbasto), 0);
	}
	
	@Test
	public void SiNotificoDosBusquedasDeUnaTerminalYUnaDeOtraYGeneroReporteCantResultadosPorTerminalDevuelveCantCorrectaResultadosParaCadaTerminal(){
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaAbasto2);
		manejadorDeReportes.notificarBusqueda(busquedaCaballito1);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteDeResultadoTotalesPorTerminales();
		
		Integer cantResultadosTotalesAbasto = busquedaAbasto1.getCantResultados()+busquedaAbasto2.getCantResultados();
		String terminalAbasto = busquedaAbasto1.getNombreTerminal();
		
		Assert.assertEquals(cantResultadosTotalesAbasto, reporte.get(terminalAbasto), 0);
		Assert.assertEquals(busquedaCaballito1.getCantResultados(),reporte.get(busquedaCaballito1.getNombreTerminal()),0);
	}
}
