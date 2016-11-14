package pois;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import adapters.ComponenteExternoAdapter;
import herramientas.EntityManagerHelper;
import herramientas.JedisHelper;

public class ManejadorDePois {

	private static ManejadorDePois singleton = null;

	private List<POI> listaPoisInternos;

	private List<ComponenteExternoAdapter> adaptersComponentesExternos;

	private ManejadorDePois() {
		this.adaptersComponentesExternos = new ArrayList<ComponenteExternoAdapter>();
		this.inicializarListaPois();		
	}

	private void inicializarListaPois() {
		listaPoisInternos = EntityManagerHelper.traerTodosLosPOIs();
	}

	public static ManejadorDePois getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDePois();
		}

		return singleton;
	}

	public List<POI> buscarPOIs(String descripcion) {

		List<POI> poisEncontrados = JedisHelper.buscarPoisEnRedis(descripcion);

		if (poisEncontrados.isEmpty()) {
			poisEncontrados = buscarYPersistirPoisServiciosExternos(descripcion);
		}

		poisEncontrados.addAll(this.buscarEnInternos(descripcion));

		return poisEncontrados;

	}

	public void setListaPoisInternos(List<POI> listaPoisNueva) {
		listaPoisInternos = listaPoisNueva;
		EntityManagerHelper.actualizarPOIs(listaPoisNueva);
	}

	public void setListaAdapters(List<ComponenteExternoAdapter> listaAdapters) {
		adaptersComponentesExternos = listaAdapters;
	}

	public void agregarPoiInterno(POI poi) {
		if (this.estaEnLaLista(listaPoisInternos, poi))
			actualizarPoiInterno(poi);
		else {
			listaPoisInternos.add(poi);
			this.persistirPOIInterno(poi);
		}

	}

	private void persistirPOIExterno(POI poiExterno) {
		JedisHelper.persistirPoiExterno(poiExterno);
	}

	private void persistirPOIInterno(POI poi) {
		EntityManagerHelper.persistir(poi);
	}

	private void actualizarPoiInterno(POI poiNuevo) {

		POI poiViejoLista = listaPoisInternos.stream().filter(unPoi -> unPoi.esIgualA(poiNuevo)).findFirst().get();

		listaPoisInternos.remove(poiViejoLista);
		listaPoisInternos.add(poiNuevo);

		EntityManagerHelper.actualizarPoi(poiNuevo, poiViejoLista.getId());

	}

	private boolean estaEnLaLista(List<POI> listaPois, POI poiBuscado) {
		return listaPois.stream().anyMatch(unPoi -> poiBuscado.esIgualA(unPoi));
	}

	private List<POI> buscarYPersistirPoisServiciosExternos(String descripcion) {

		List<POI> poisExternos = this.damePoisServiciosExternos(descripcion);
		this.persistirPoisExternos(poisExternos);

		return poisExternos;

	}

	private void persistirPoisExternos(List<POI> poisExternosNuevos) {
		poisExternosNuevos.stream().forEach(poiExterno -> this.persistirPOIExterno(poiExterno));

	}

	private ArrayList<POI> damePoisServiciosExternos(String descripcion) {

		return (ArrayList<POI>) adaptersComponentesExternos.stream()
				.map(adapter -> adapter.buscarPoisExternos(descripcion)).flatMap(listaPois -> listaPois.stream())
				.collect(Collectors.toList());
	}

	public void agregarPoisInternos(List<POI> poisNuevos) {
		poisNuevos.stream().forEach(poi -> this.agregarPoiInterno(poi));
	}

	public Boolean poiDisponible(POI poi, DateTime momento) {
		return poi.estaDisponible(momento);
	}

	public void eliminarPOIInterno(POI poi) {
		listaPoisInternos.remove(poi);
		this.eliminarPoiInternoDeBD(poi);
	}

	private void eliminarPoiInternoDeBD(POI poi) {
		EntityManagerHelper.eliminarPoi(poi);
	}

	private List<POI> buscarEnInternos(String descripcion) {
		return listaPoisInternos.stream().filter(poi -> poi.contiene(descripcion)).collect(Collectors.toList());
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

	public Integer actualizarEtiquetasLocalesComercialesYRetornarCantidadModificados(String nombre,
			List<String> etiquetas) {

		return EntityManagerHelper.actualizarEtiquetasComerciosYRetornarCantidadModificados(nombre, etiquetas);
	}

	public POI buscarPOI(String nombrePOI, Direccion direccionPOI) {
		// Si no encuentra ninguno tira IndexOutOfBoundsException

		return listaPoisInternos.stream().filter(poi -> poi.getNombre().equalsIgnoreCase(nombrePOI)
				&& poi.getDireccion().esLaMismaDireccionQue(direccionPOI)).collect(Collectors.toList()).get(0);
	}

	public List<POI> getListaPoisInternos() {
		return listaPoisInternos;
	}

	public void clearListaPoisInternos() {
		listaPoisInternos.clear();
	}

	public POI getPOI(long id) {
		return EntityManagerHelper.find(POI.class, id);
	}

	public List<POI> traerTodosLosPois() {
		return EntityManagerHelper.traerTodosLosPOIs();
	}

}
