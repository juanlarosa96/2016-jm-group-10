package eventosBusqueda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import herramientas.ManejadorDeFechas;

public class ManejadorDeReportes implements InteresadoEnBusquedas {

	List<Busqueda> busquedas;
	private static ManejadorDeReportes singleton;

	private ManejadorDeReportes() {

		busquedas = new ArrayList<Busqueda>();
	}

	@Override
	public void notificarBusqueda(Busqueda unaBusqueda) {
		busquedas.add(unaBusqueda);
	}

	public static ManejadorDeReportes getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeReportes();
		}

		return singleton;
	}

	public HashMap<String, Integer> generarReporteBusquedasPorFecha() {

		HashMap<String, Integer> reporte = new HashMap<String, Integer>();

		Set<String> fechas = busquedas.stream()
				.map(busqueda -> ManejadorDeFechas.convertirFechaAString(busqueda.getFecha()))
				.collect(Collectors.toSet());

		fechas.stream().forEach(fecha -> {
			Integer cantBusquedas = this.contarBusquedasPorFecha(fecha);
			reporte.put(fecha, cantBusquedas);
		});

		return reporte;

	}

	private Integer contarBusquedasPorFecha(String fecha) {

		return busquedas.stream()
				.filter(busqueda -> fecha.equals(ManejadorDeFechas.convertirFechaAString(busqueda.getFecha())))
				.collect(Collectors.toList()).size();
	}

	public HashMap<String, List<Integer>> generarReporteDeResultadosParcialesPorBusquedaPorTerminal() {
		
		HashMap<String, List<Integer>> reporte = new HashMap<String, List<Integer>>();
		
		Set<String> terminales = busquedas.stream().map(busqueda -> busqueda.getNombreTerminal())
				.collect(Collectors.toSet());

		terminales.stream().forEach(terminal -> {
			List<Integer> cantResultadosParciales = this.contarResultadosParcialesPorTerminal(terminal);
			reporte.put(terminal, cantResultadosParciales);
		});

		return reporte;	
	}

	private List<Integer> contarResultadosParcialesPorTerminal(String terminal) {
		
		return busquedas.stream().filter(busqueda -> terminal.equals(busqueda.getNombreTerminal()))
				.map(busqueda -> busqueda.getCantResultados()).collect(Collectors.toList());
	}

	public HashMap<String, Integer> generarReporteDeResultadoTotalesPorTerminales() {

		HashMap<String, Integer> reporte = new HashMap<String, Integer>();

		Set<String> terminales = busquedas.stream().map(busqueda -> busqueda.getNombreTerminal())
				.collect(Collectors.toSet());

		terminales.stream().forEach(terminal -> {
			Integer cantResultados = this.contarResultadosTotalesPorTerminal(terminal);
			reporte.put(terminal, cantResultados);
		});

		return reporte;
	}

	private Integer contarResultadosTotalesPorTerminal(String terminal) {

		return busquedas.stream().filter(busqueda -> terminal.equals(busqueda.getNombreTerminal()))
				.mapToInt(busqueda -> busqueda.getCantResultados()).sum();

	}

	public void limpiarBusquedas() {
		busquedas.clear();
	}
}
