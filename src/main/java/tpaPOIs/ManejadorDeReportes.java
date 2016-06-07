package tpaPOIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

		return reporte;
	}

	public HashMap<String, Integer> generarReporteDeResultadoTotalesPorTerminales() {

		HashMap<String, Integer> reporte = new HashMap<String, Integer>();

		return reporte;
	}
}
