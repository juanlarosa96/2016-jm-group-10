package tpaPOIs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.DateTime;

public class ManejadorDePois {

	private static ManejadorDePois singleton = null;
	public static List<POI> listaPois;
	private static List<ComponenteExternoAdapter> adaptersComponentesExternos;

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

	public void eliminarPOI(POI poi) {
		listaPois.remove(poi);
	}

	private boolean estaEnLaLista(POI poiBuscado) {
		return listaPois.stream().anyMatch(unPoi -> poiBuscado.esIgualA(unPoi));

	}

	public List<POI> buscarPOIs(String descripcion) {
		this.consultarPoisExternos(descripcion);
		return listaPois.stream().filter(poi -> poi.contiene(descripcion)).collect(Collectors.toList());

	}

	private void consultarPoisExternos(String descripcion) {

		this.agregarPois(this.damePoisExternos(descripcion));

	}

	private void agregarPois(ArrayList<POI> listaDePois) {
		listaDePois.stream().forEach(poi -> this.agregarPoi(poi));
	}

	public Boolean poiDisponible(POI poi, DateTime momento) {
		return poi.estaDisponible(momento);
	}

	public List<POI> buscarPoisDisponibles(String descripcion, DateTime momento) {
		// no sirve para buscar si esta disponible un servicio en un cgp
		return this.buscarPOIs(descripcion).stream().filter(poi -> poi.estaDisponible(momento)).collect(Collectors.toList());
	}

	public List<POI> buscarServicioDisponible(String servicio, DateTime momento) {
		// todos los pois que no sean cgps responden false a
		// estaDisponibleServicio
		return this.buscarPOIs(servicio).stream().filter(poi -> poi.estaDisponibleServicio(servicio, momento))
				.collect(Collectors.toList());

	}

	public ArrayList<POI> damePoisExternos(String descripcion) {

		return (ArrayList<POI>) adaptersComponentesExternos.stream()
				.map(adapter -> adapter.buscarPoisExternos(descripcion)).flatMap(listaPois -> listaPois.stream())
				.collect(Collectors.toList());
	}

}
