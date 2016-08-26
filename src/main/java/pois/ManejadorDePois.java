package pois;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import adapters.ComponenteExternoAdapter;

public class ManejadorDePois {

	private static ManejadorDePois singleton = null;
	public List<POI> listaPois;
	private List<ComponenteExternoAdapter> adaptersComponentesExternos;

	private ManejadorDePois() {

	}

	public static ManejadorDePois getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDePois();
		}

		return singleton;
	}

	public void setListaPois(List<POI> unaListaDePois) {
		listaPois = unaListaDePois;
	}

	public void setListaAdapters(List<ComponenteExternoAdapter> listaAdapters) {
		adaptersComponentesExternos = listaAdapters;
	}

	public void agregarPoi(POI poi) {
		if (this.estaEnLaLista(poi))
			actualizarPoi(poi);
		else
			listaPois.add(poi);
	}

	private void actualizarPoi(POI poiNuevo) {

		POI poiViejo = listaPois.stream().filter(unPoi -> unPoi.esIgualA(poiNuevo)).findFirst().get();

		this.eliminarPOI(poiViejo);

		listaPois.add(poiNuevo);
	}

	private boolean estaEnLaLista(POI poiBuscado) {
		return listaPois.stream().anyMatch(unPoi -> poiBuscado.esIgualA(unPoi));

	}

	private void consultarPoisExternos(String descripcion) {

		this.agregarPois(this.damePoisExternos(descripcion));

	}

	private ArrayList<POI> damePoisExternos(String descripcion) {

		return (ArrayList<POI>) adaptersComponentesExternos.stream()
				.map(adapter -> adapter.buscarPoisExternos(descripcion)).flatMap(listaPois -> listaPois.stream())
				.collect(Collectors.toList());
	}

	private void agregarPois(ArrayList<POI> listaDePois) {
		listaDePois.stream().forEach(poi -> this.agregarPoi(poi));
	}

	public Boolean poiDisponible(POI poi, DateTime momento) {
		return poi.estaDisponible(momento);
	}

	public void eliminarPOI(POI poi) {
		listaPois.remove(poi);
	}

	public List<POI> buscarPOIs(String descripcion) {
		this.consultarPoisExternos(descripcion);
		return listaPois.stream().filter(poi -> poi.contiene(descripcion)).collect(Collectors.toList());

	}

	public List<POI> buscarPoisDisponibles(String descripcion, DateTime momento) {
		// no sirve para buscar si esta disponible un servicio en un cgp
		return this.buscarPOIs(descripcion).stream().filter(poi -> poi.estaDisponible(momento))
				.collect(Collectors.toList());
	}

	public List<POI> buscarServicioDisponible(String servicio, DateTime momento) {
		// todos los pois que no sean cgps responden false a
		// estaDisponibleServicio
		return this.buscarPOIs(servicio).stream().filter(poi -> poi.estaDisponibleServicio(servicio, momento))
				.collect(Collectors.toList());

	}

	public Integer actualizarEtiquetasLocalesComerciales(String nombre, List<String> etiquetas) {
		Stream<POI> streamPois = listaPois.stream().filter(comercio -> comercio.getNombre().equals(nombre));
		List<POI> lista = streamPois.collect(Collectors.toList());	
		lista.stream().forEach(comercio -> comercio.setEtiquetas(etiquetas));
		return lista.size();

	}

	public POI buscarPOI(String nombrePOI, Direccion direccionPOI) {
		return listaPois.stream().filter(
				poi -> poi.getNombre().equalsIgnoreCase(nombrePOI) && poi.getDireccion().esLaMismaDireccionQue(direccionPOI))
				.collect(Collectors.toList()).get(0);
	
		// Si no encuentra ninguno tira IndexOutOfBoundsException
	}

}
