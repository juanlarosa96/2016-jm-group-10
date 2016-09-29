package eventosBusqueda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

import herramientas.ManejadorDeFechas;

public class ManejadorDeReportes extends InteresadoEnBusquedas {

	private Integer cantBusquedasPorPersistir = 0;
	private Integer maxBusquedasPendientesPersist = 10;
	private static ManejadorDeReportes singleton;
	private Morphia persistidor = new Morphia();
	private Datastore datastore;

	private List<ResultadoBusqueda> resultadosBusquedas;

	private ManejadorDeReportes() {
	}

	public static ManejadorDeReportes getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeReportes();
			singleton.inicializarMongoDB();
			singleton.inicializarListaBusquedas();

		}
		return singleton;
	}

	private void inicializarMongoDB() {
		persistidor.mapPackage("eventosBusqueda");
		persistidor.mapPackage("pois");
		datastore = persistidor.createDatastore(new MongoClient(), "tpaPOIs");
		datastore.ensureIndexes();
	}

	private void inicializarListaBusquedas() {
		resultadosBusquedas = new ArrayList<ResultadoBusqueda>();
		resultadosBusquedas.addAll(this.busquedasPersistidas());
	}

	private List<ResultadoBusqueda> busquedasPersistidas() {
		return datastore.createQuery(ResultadoBusqueda.class).asList();
	}

	@Override
	public void notificarBusqueda(ResultadoBusqueda unaBusqueda) {
		resultadosBusquedas.add(unaBusqueda);
		cantBusquedasPorPersistir++;

		if (cantBusquedasPorPersistir >= maxBusquedasPendientesPersist) {

			Integer cantBusquedas = resultadosBusquedas.size();

			List<ResultadoBusqueda> busquedasPorPersistir = resultadosBusquedas.stream().skip(cantBusquedas - maxBusquedasPendientesPersist)
					.collect(Collectors.toList());
			busquedasPorPersistir.stream().forEach(busq -> datastore.save(busq));

			cantBusquedasPorPersistir = 0;
		}
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
		return resultadosBusquedas.stream()
				.map(busqueda -> ManejadorDeFechas.convertirFechaAString(busqueda.getFecha()))
				.collect(Collectors.toSet());
	}

	public void limpiarBusquedas() {
		resultadosBusquedas.clear();
		cantBusquedasPorPersistir = 0;
		this.eliminarBusquedasDeBD();
	}

	private void eliminarBusquedasDeBD() {
		Query<ResultadoBusqueda> resultadosABorrar = datastore.createQuery(ResultadoBusqueda.class);
		datastore.delete(resultadosABorrar);
	}

	public List<ResultadoBusqueda> getResultadosBusquedas() {
		return resultadosBusquedas;
	}

	public void setResultadosBusquedas(List<ResultadoBusqueda> busquedasNuevas) {
		this.resultadosBusquedas = busquedasNuevas;

		this.eliminarBusquedasDeBD();

		busquedasNuevas.stream().forEach(busq -> datastore.save(busq));
	}

	public Datastore getDatastore() {
		return datastore;
	}

	public void setMaxBusquedasPendientesPersist(Integer maxBusquedasPendientesPersist) {
		this.maxBusquedasPendientesPersist = maxBusquedasPendientesPersist;
	}

}
