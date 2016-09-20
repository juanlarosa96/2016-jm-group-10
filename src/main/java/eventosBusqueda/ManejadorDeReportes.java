package eventosBusqueda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

import herramientas.EntityManagerHelper;
import herramientas.ManejadorDeFechas;

@Entity
public class ManejadorDeReportes extends InteresadoEnBusquedas {

	@Transient
	private Integer cantBusquedasPorPersistir = 0;
	@Transient
	private Integer maxBusquedasPendientesPersist = 10;
	@Transient
	private static ManejadorDeReportes singleton;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn
	private List<ResultadoBusqueda> resultadosBusquedas;

	private ManejadorDeReportes() {
	}

	public static ManejadorDeReportes getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeReportes();
			singleton.inicializarListaBusquedas();
		}
		return singleton;
	}

	private void inicializarListaBusquedas() {
		resultadosBusquedas = new ArrayList<ResultadoBusqueda>();
		resultadosBusquedas.addAll(this.busquedasPersistidas());
	}

	@SuppressWarnings("unchecked")
	private List<ResultadoBusqueda> busquedasPersistidas() {
		return EntityManagerHelper.getEntityManager().createQuery("from ResultadoBusqueda").getResultList();
	}

	@Override
	public void notificarBusqueda(ResultadoBusqueda unaBusqueda) {

		resultadosBusquedas.add(unaBusqueda);
		cantBusquedasPorPersistir++;

		if (cantBusquedasPorPersistir >= maxBusquedasPendientesPersist) {

			EntityManagerHelper.beginTransaction();

			Integer cantBusquedas = resultadosBusquedas.size();

			List<ResultadoBusqueda> busquedasPorPersistir = resultadosBusquedas.stream().skip(cantBusquedas - 10)
					.collect(Collectors.toList());
			busquedasPorPersistir.stream().forEach(busq -> EntityManagerHelper.persist(busq));

			EntityManagerHelper.commit();

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
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.getEntityManager().createQuery("DELETE FROM ResultadoBusqueda").executeUpdate();
		EntityManagerHelper.commit();
	}

	public List<ResultadoBusqueda> getResultadosBusquedas() {
		return resultadosBusquedas;
	}

	public void setResultadosBusquedas(List<ResultadoBusqueda> busquedasNuevas) {
		this.resultadosBusquedas = busquedasNuevas;

		EntityManagerHelper.beginTransaction();

		EntityManagerHelper.createQuery("DELETE FROM ResultadoBusqueda").executeUpdate();
		
		busquedasNuevas.stream().forEach(busq -> EntityManagerHelper.persist(busq));

		EntityManagerHelper.commit();
	}

}
