package tests;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import eventosBusqueda.ResultadoBusqueda;
import fixtures.FixtureBanco;
import fixtures.FixtureComercio;
import eventosBusqueda.ManejadorDeReportes;
import herramientas.ManejadorDeFechas;
import pois.POI;
import pois.POIDTO;

public class ManejadorDeReportesTest {

	private ResultadoBusqueda busquedaAbasto1;
	private ResultadoBusqueda busquedaRecoleta1;
	private DateTime fecha1;

	private ManejadorDeReportes manejadorDeReportes;
	private ResultadoBusqueda busquedaCaballito1;
	private DateTime fecha2;
	private ResultadoBusqueda busquedaAbasto2;
	private ResultadoBusqueda busquedaAbastoCon0Resultados;

	private ArrayList<POIDTO> listaSize0;
	private ArrayList<POIDTO> listaSize3;
	private ArrayList<POIDTO> listaSize4;
	private ArrayList<POIDTO> listaSize5;
	private ArrayList<POIDTO> listaSize8;

	private POIDTO bancoDTO;
	private POIDTO comercioDTO;

	@SuppressWarnings("unchecked")
	@Before
	public void init() {
		fecha1 = new DateTime(2016, 06, 07, 20, 51);
		fecha2 = new DateTime(2014, 03, 05, 10, 20);

		listaSize0 = Mockito.mock(ArrayList.class);
		listaSize3 = Mockito.mock(ArrayList.class);
		listaSize4 = Mockito.mock(ArrayList.class);
		listaSize5 = Mockito.mock(ArrayList.class);
		listaSize8 = Mockito.mock(ArrayList.class);

		bancoDTO = FixtureBanco.dameUnBancoValido().convertiteAPoiDto();
		comercioDTO = FixtureComercio.dameComercioValido().convertiteAPoiDto();

		List<POIDTO> poisDto3 = new ArrayList<POIDTO>() {
			{
				add(bancoDTO);
				add(comercioDTO);
				add(bancoDTO);
			}
		};
		List<POIDTO> poisDto4 = new ArrayList<POIDTO>() {
			{
				add(bancoDTO);
				add(comercioDTO);
				add(bancoDTO);
				add(bancoDTO);
			}
		};
		List<POIDTO> poisDto5 = new ArrayList<POIDTO>() {
			{
				add(bancoDTO);
				add(comercioDTO);
				add(bancoDTO);
				add(bancoDTO);
				add(bancoDTO);
			}
		};
		List<POIDTO> poisDto8 = new ArrayList<POIDTO>() {
			{
				add(bancoDTO);
				add(comercioDTO);
				add(bancoDTO);
				add(bancoDTO);
				add(bancoDTO);
				add(comercioDTO);
				add(bancoDTO);
				add(bancoDTO);
			}
		};

		when(listaSize0.size()).thenReturn(0);
		when(listaSize3.size()).thenReturn(3);
		when(listaSize4.size()).thenReturn(4);
		when(listaSize5.size()).thenReturn(5);
		when(listaSize8.size()).thenReturn(8);

		busquedaAbasto1 = new ResultadoBusqueda("terminalAbasto", listaSize3, fecha1, 10.0, "hospital");
		busquedaRecoleta1 = new ResultadoBusqueda("terminalRecoleta", listaSize5, fecha1, 12.5, "libreria");
		busquedaCaballito1 = new ResultadoBusqueda("terminalCaballito", listaSize4, fecha2, 3.4, "restaurant");
		busquedaAbasto2 = new ResultadoBusqueda("terminalAbasto", listaSize8, fecha2, 2.5, "burguer");
		busquedaAbastoCon0Resultados = new ResultadoBusqueda("terminalAbasto", listaSize0, fecha2, 1.5,
				"fabrica de pizza");

		busquedaAbasto1.setPoisEncontrados(poisDto3);
		busquedaAbasto2.setPoisEncontrados(poisDto8);
		busquedaCaballito1.setPoisEncontrados(poisDto4);
		busquedaRecoleta1.setPoisEncontrados(poisDto5);
		busquedaAbastoCon0Resultados.setPoisEncontrados(new ArrayList<POIDTO>());

		manejadorDeReportes = ManejadorDeReportes.getInstance();
		manejadorDeReportes.setMaxBusquedasPendientesPersist(1);
	}

	@After
	public void after(){
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
	public void SiNoNotificoNingunaBusquedaMeGeneraUnReporteDeCantidadDeResultadosTotalesPorTerminalVacio() {
		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteDeResultadoTotalesPorTerminales();

		Assert.assertTrue(reporte.isEmpty());
	}

	@Test
	public void SiNotificoDosBusquedasConIgualTerminalYGeneroReporteDeCantidadDeResultadosTotalesPorTerminalMeDevuelveLaSumaDeResultadosDeLasDosBusquedasParaEsaTerminal() {
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaAbasto2);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteDeResultadoTotalesPorTerminales();

		Integer cantResultadosTotalesAbasto = busquedaAbasto1.getCantResultados() + busquedaAbasto2.getCantResultados();
		String terminalAbasto = busquedaAbasto1.getNombreTerminal();

		Assert.assertEquals(cantResultadosTotalesAbasto, reporte.get(terminalAbasto), 0);
	}

	@Test
	public void SiNotificoDosBusquedasDeUnaTerminalYUnaDeOtraYGeneroReporteCantResultadosPorTerminalDevuelveCantCorrectaResultadosParaCadaTerminal() {
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaAbasto2);
		manejadorDeReportes.notificarBusqueda(busquedaCaballito1);

		HashMap<String, Integer> reporte = manejadorDeReportes.generarReporteDeResultadoTotalesPorTerminales();

		Integer cantResultadosTotalesAbasto = busquedaAbasto1.getCantResultados() + busquedaAbasto2.getCantResultados();
		String terminalAbasto = busquedaAbasto1.getNombreTerminal();

		Assert.assertEquals(cantResultadosTotalesAbasto, reporte.get(terminalAbasto), 0);
		Assert.assertEquals(busquedaCaballito1.getCantResultados(), reporte.get(busquedaCaballito1.getNombreTerminal()),
				0);
	}

	@Test
	public void SiNotificoDosBusquedasConIgualTerminalYGeneroReporteDeCantidadDeResultadosParcialesPorTerminalMeDevuelveResultadosParcialesDeLasDosBusquedasParaEsaTerminal() {
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaAbasto2);

		HashMap<String, List<Integer>> reporte = manejadorDeReportes
				.generarReporteDeResultadosParcialesPorBusquedaPorTerminal();

		Integer resultadosParciales1 = busquedaAbasto1.getCantResultados();
		Integer resultadosParciales2 = busquedaAbasto2.getCantResultados();
		String terminalAbasto = busquedaAbasto1.getNombreTerminal();

		Assert.assertEquals(resultadosParciales1, reporte.get(terminalAbasto).get(0), 0);
		Assert.assertEquals(resultadosParciales2, reporte.get(terminalAbasto).get(1), 0);
	}

	@Test
	public void SiNotificoDosBusquedasDeUnaTerminalYUnaDeOtraYGeneroReporteCantResultadosParcialesPorTerminalDevuelveCantCorrectaResultadosParaCadaTerminal() {
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaAbasto2);
		manejadorDeReportes.notificarBusqueda(busquedaCaballito1);

		HashMap<String, List<Integer>> reporte = manejadorDeReportes
				.generarReporteDeResultadosParcialesPorBusquedaPorTerminal();

		Integer resultadosParciales1 = busquedaAbasto1.getCantResultados();
		Integer resultadosParciales2 = busquedaAbasto2.getCantResultados();
		Integer resultadosParciales3 = busquedaCaballito1.getCantResultados();
		String terminalAbasto = busquedaAbasto1.getNombreTerminal();
		String terminalCaballito = busquedaCaballito1.getNombreTerminal();

		Assert.assertEquals(resultadosParciales1, reporte.get(terminalAbasto).get(0), 0);
		Assert.assertEquals(resultadosParciales2, reporte.get(terminalAbasto).get(1), 0);
		Assert.assertEquals(resultadosParciales3, reporte.get(terminalCaballito).get(0), 0);
	}

	@Test
	public void SiNotificoDosBusquedasDeUnTerminalConUnaBusquedaQueArroja0ResultadosElReporteDeBusquedasParcialesGeneraCorrectamenteAmbosResultados() {
		manejadorDeReportes.notificarBusqueda(busquedaAbasto1);
		manejadorDeReportes.notificarBusqueda(busquedaAbastoCon0Resultados);

		HashMap<String, List<Integer>> reporte = manejadorDeReportes
				.generarReporteDeResultadosParcialesPorBusquedaPorTerminal();

		Integer resultadosParciales1 = busquedaAbasto1.getCantResultados();
		Integer resultadosParciales2 = busquedaAbastoCon0Resultados.getCantResultados();

		String terminalAbasto = busquedaAbasto1.getNombreTerminal();

		Assert.assertEquals(resultadosParciales1, reporte.get(terminalAbasto).get(0), 0);
		Assert.assertEquals(resultadosParciales2, reporte.get(terminalAbasto).get(1), 0);
	}

}
