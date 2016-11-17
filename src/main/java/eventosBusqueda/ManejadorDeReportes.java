package eventosBusqueda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.Transient;

import herramientas.ManejadorDeFechas;
import herramientas.PersistidorMongo;

@Entity
public class ManejadorDeReportes extends InteresadoEnBusquedas {

	@Transient
	private static Boolean baseInicializada = false;

	@Transient
	private List<ResultadoBusqueda> busquedasPorPersistir;

	@Transient
	private static PersistidorMongo persistidor;

	private Integer maxBusquedasPendientesPersist = 1;

	public ManejadorDeReportes() {

		this.inicializarListaBusquedas();

		if (!baseInicializada) {
			persistidor = new PersistidorMongo();
			persistidor.inicializarDB("tpaPOIs");
			baseInicializada = true;
		}
	}

	private void inicializarListaBusquedas() {
		busquedasPorPersistir = new ArrayList<ResultadoBusqueda>();
	}

	private List<ResultadoBusqueda> busquedasPersistidas() {
		return persistidor.obtenerTodosLosResultadosBusqueda();
	}

	@Override
	public void notificarBusqueda(ResultadoBusqueda unaBusqueda) {
		busquedasPorPersistir.add(unaBusqueda);
		Integer cantBusquedas = busquedasPorPersistir.size();

		if (cantBusquedas >= maxBusquedasPendientesPersist) {

			busquedasPorPersistir.stream().forEach(busq -> persistidor.guardar(busq));
			busquedasPorPersistir.clear();
		}
	}

	private Integer contarBusquedasPorFecha(List<ResultadoBusqueda> busquedas, String fecha) {
		return busquedas.stream()
				.filter(busqueda -> fecha.equals(ManejadorDeFechas.convertirFechaAString(busqueda.getFecha())))
				.collect(Collectors.toList()).size();
	}

	public HashMap<String, Integer> generarReporteBusquedasPorFecha() {

		this.persistirBusquedasNoPersistidas();

		List<ResultadoBusqueda> busquedas = this.busquedasPersistidas();

		HashMap<String, Integer> reporte = new HashMap<String, Integer>();

		Set<String> fechas = this.obtenerSetDeFechas(busquedas);

		fechas.stream().forEach(fecha -> reporte.put(fecha, this.contarBusquedasPorFecha(busquedas, fecha)));

		return reporte;

	}

	public HashMap<String, List<Integer>> generarReporteDeResultadosParcialesPorBusquedaPorTerminal() {

		this.persistirBusquedasNoPersistidas();

		List<ResultadoBusqueda> busquedas = this.busquedasPersistidas();

		HashMap<String, List<Integer>> reporte = new HashMap<String, List<Integer>>();

		Set<String> terminales = this.obtenerSetDeTerminales(busquedas);

		terminales.stream().forEach(terminal -> reporte.put(terminal,
				this.obtenerCantidadResultadosDeTerminal(busquedas, terminal).collect(Collectors.toList())));

		return reporte;
	}

	public HashMap<String, Integer> generarReporteDeResultadoTotalesPorTerminales() {

		this.persistirBusquedasNoPersistidas();

		List<ResultadoBusqueda> busquedas = this.busquedasPersistidas();

		HashMap<String, Integer> reporte = new HashMap<String, Integer>();

		Set<String> terminales = this.obtenerSetDeTerminales(busquedas);

		terminales.stream().forEach(terminal -> reporte.put(terminal,
				this.obtenerCantidadResultadosDeTerminal(busquedas, terminal).mapToInt(i -> i).sum()));

		return reporte;
	}

	private Stream<Integer> obtenerCantidadResultadosDeTerminal(List<ResultadoBusqueda> busquedas, String terminal) {
		return busquedas.stream().filter(busqueda -> terminal.equals(busqueda.getNombreTerminal()))
				.map(busqueda -> busqueda.getCantResultados());
	}

	private Set<String> obtenerSetDeTerminales(List<ResultadoBusqueda> busquedas) {
		return busquedas.stream().map(busqueda -> busqueda.getNombreTerminal()).collect(Collectors.toSet());
	}

	private Set<String> obtenerSetDeFechas(List<ResultadoBusqueda> busquedas) {
		return busquedas.stream().map(busqueda -> ManejadorDeFechas.convertirFechaAString(busqueda.getFecha()))
				.collect(Collectors.toSet());
	}

	public void limpiarTodasLasBusquedas() {
		busquedasPorPersistir.clear();
		this.eliminarBusquedasDeBD();
	}

	private void eliminarBusquedasDeBD() {
		persistidor.borrarTodosLosResultadosBusquedaDeBD();
	}

	public List<ResultadoBusqueda> getBusquedasPorPersistir() {
		return busquedasPorPersistir;
	}

	public void setBusquedasPorPersistir(List<ResultadoBusqueda> busquedasNuevas) {
		this.busquedasPorPersistir = busquedasNuevas;
	}

	public void setMaxBusquedasPendientesPersist(Integer maxBusquedasPendientesPersist) {
		this.maxBusquedasPendientesPersist = maxBusquedasPendientesPersist;
	}

	private void persistirBusquedasNoPersistidas() {
		if (!busquedasPorPersistir.isEmpty()) {
			busquedasPorPersistir.stream().forEach(busq -> persistidor.guardar(busq));
			busquedasPorPersistir.clear();
		}
	}
	
	public String getNombreAccion(){
		return "Reportar búsquedas";
	}

}
