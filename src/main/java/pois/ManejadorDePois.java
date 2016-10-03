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
	private List<POI> listaPoisExternos;

	private List<ComponenteExternoAdapter> adaptersComponentesExternos;

	private Integer cantBusquedasSinExternos = 100;

	private ManejadorDePois() {
		this.inicializarListaPois();
	}
	
	private void inicializarListaPois() {
		listaPoisInternos = EntityManagerHelper.traerTodosLosPOIs();
		listaPoisExternos = JedisHelper.obtenerPoisExternosDeRedis(); 
	}

	public static ManejadorDePois getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDePois();
		}

		return singleton;
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

	public void agregarPoiExterno(POI poiExterno) {
		if (this.estaEnLaLista(listaPoisExternos, poiExterno))
			actualizarPoiExterno(poiExterno);
		else {
			listaPoisExternos.add(poiExterno);
			this.persistirPOIExterno(poiExterno);
		}

	}

	private void persistirPOIExterno(POI poiExterno) {
		JedisHelper.persistirPoiExterno(poiExterno);


	}

	private void actualizarPoiExterno(POI poiExternoNuevo) {

		POI poiViejoLista = listaPoisExternos.stream().filter(unPoi -> unPoi.esIgualA(poiExternoNuevo)).findFirst()
				.get();

		listaPoisExternos.remove(poiViejoLista);
		listaPoisExternos.add(poiExternoNuevo);

		JedisHelper.actualizarPoiExterno(poiExternoNuevo, poiViejoLista);
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

	private void consultarPoisExternos(String descripcion) {

		this.agregarPoisExternos(this.damePoisExternos(descripcion));

	}

	private void agregarPoisExternos(List<POI> poisExternosNuevos) {
		poisExternosNuevos.stream().forEach(poiExterno -> this.agregarPoiExterno(poiExterno));

	}

	private ArrayList<POI> damePoisExternos(String descripcion) {

		return (ArrayList<POI>) adaptersComponentesExternos.stream().map(adapter -> adapter.buscarPoisExternos(descripcion))
				.flatMap(listaPois -> listaPois.stream()).collect(Collectors.toList());
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

	public List<POI> buscarPOIs(String descripcion) {

		if (++cantBusquedasSinExternos > 100) {
			consultarPoisExternos(descripcion);
			cantBusquedasSinExternos = 0;
		}

		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados.addAll(this.buscarEnInternos(descripcion));
		poisEncontrados.addAll(this.buscarEnExternos(descripcion));

		return poisEncontrados;

	}

	private List<POI> buscarEnExternos(String descripcion) {
		return this.buscarEnListaPois(listaPoisExternos, descripcion);

	}

	private List<POI> buscarEnInternos(String descripcion) {
		return this.buscarEnListaPois(listaPoisInternos, descripcion);

	}

	private List<POI> buscarEnListaPois(List<POI> listaPois, String descripcion) {
		return listaPois.stream().filter(poi -> poi.contiene(descripcion)).collect(Collectors.toList());

	}

	public List<POI> buscarPoisDisponibles(String descripcion, DateTime momento) {
		// no sirve para buscar si esta disponible un servicio en un cgp
		return this.buscarPOIs(descripcion).stream().filter(poi -> poi.estaDisponible(momento)).collect(Collectors.toList());
	}

	public List<POI> buscarServicioDisponible(String servicio, DateTime momento) {
		// todos los pois que no sean cgps responden false a
		// estaDisponibleServicio
		return this.buscarPOIs(servicio).stream().filter(poi -> poi.estaDisponibleServicio(servicio, momento)).collect(Collectors.toList());

	}

	public Integer actualizarEtiquetasLocalesComercialesYRetornarCantidadModificados(String nombre, List<String> etiquetas) {

		return EntityManagerHelper.actualizarEtiquetasComerciosYRetornarCantidadModificados(nombre, etiquetas);
	}

	public POI buscarPOI(String nombrePOI, Direccion direccionPOI) {
		// Si no encuentra ninguno tira IndexOutOfBoundsException

		return listaPoisInternos.stream().filter(poi -> poi.getNombre().equalsIgnoreCase(nombrePOI) && poi.getDireccion().esLaMismaDireccionQue(direccionPOI))
				.collect(Collectors.toList()).get(0);
	}

	public List<POI> getListaPoisInternos() {
		return listaPoisInternos;
	}

	public List<POI> getListaPoisExternos() {
		return listaPoisExternos;
	}

	public void activarBusquedaPoisExternos() {
		this.cantBusquedasSinExternos = 100;
	}

	public void clearListaPoisExternos() {
		listaPoisExternos.clear();
	}

}
