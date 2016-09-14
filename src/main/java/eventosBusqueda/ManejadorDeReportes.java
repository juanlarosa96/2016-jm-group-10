package eventosBusqueda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

import herramientas.ManejadorDeFechas;

@Entity
public class ManejadorDeReportes extends InteresadoEnBusquedas {
	
	@OneToMany @JoinColumn
	private List<ResultadoBusqueda> resultadosBusquedas;
	
	@Transient
	private static ManejadorDeReportes singleton;

	private ManejadorDeReportes() {

		resultadosBusquedas = new ArrayList<ResultadoBusqueda>();
	}

	@Override
	public void notificarBusqueda(ResultadoBusqueda unaBusqueda) {
		resultadosBusquedas.add(unaBusqueda);
	}

	public static ManejadorDeReportes getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeReportes();
		}

		return singleton;
	}

	private Integer contarBusquedasPorFecha(String fecha) {
		return resultadosBusquedas.stream()
				.filter(busqueda -> fecha.equals(ManejadorDeFechas.convertirFechaAString(busqueda.getFecha())))
				.collect(Collectors.toList()).size();
	}

	public HashMap<String, Integer> generarReporteBusquedasPorFecha() {

		HashMap<String, Integer> reporte = new HashMap<String, Integer>();

		Set<String> fechas = this.obtenerSetDeFechas();

		fechas.stream().forEach(fecha -> reporte.put(fecha, this.contarBusquedasPorFecha(fecha)));

		return reporte;

	}

	public HashMap<String, List<Integer>> generarReporteDeResultadosParcialesPorBusquedaPorTerminal() {

		HashMap<String, List<Integer>> reporte = new HashMap<String, List<Integer>>();

		Set<String> terminales = this.obtenerSetDeTerminales();

		terminales.stream().forEach(terminal -> reporte.put(terminal,
				this.obtenerCantidadResultadosDeTerminal(terminal).collect(Collectors.toList())));

		return reporte;
	}

	public HashMap<String, Integer> generarReporteDeResultadoTotalesPorTerminales() {

		HashMap<String, Integer> reporte = new HashMap<String, Integer>();

		Set<String> terminales = this.obtenerSetDeTerminales();

		terminales.stream().forEach(terminal -> reporte.put(terminal,
				this.obtenerCantidadResultadosDeTerminal(terminal).mapToInt(i -> i).sum()));

		return reporte;
	}

	private Stream<Integer> obtenerCantidadResultadosDeTerminal(String terminal) {
		return resultadosBusquedas.stream().filter(busqueda -> terminal.equals(busqueda.getNombreTerminal()))
				.map(busqueda -> busqueda.getCantResultados());
	}

	private Set<String> obtenerSetDeTerminales() {
		return resultadosBusquedas.stream().map(busqueda -> busqueda.getNombreTerminal()).collect(Collectors.toSet());
	}

	private Set<String> obtenerSetDeFechas() {
		return resultadosBusquedas.stream().map(busqueda -> ManejadorDeFechas.convertirFechaAString(busqueda.getFecha()))
				.collect(Collectors.toSet());
	}

	public void limpiarBusquedas() {
		resultadosBusquedas.clear();
	}

	public List<ResultadoBusqueda> getResultadosBusquedas() {
		return resultadosBusquedas;
	}

	public void setResultadosBusquedas(List<ResultadoBusqueda> resultadosBusquedas) {
		this.resultadosBusquedas = resultadosBusquedas;
	}
	
	
}
