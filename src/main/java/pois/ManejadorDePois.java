package pois;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import adapters.ComponenteExternoAdapter;
import herramientas.EntityManagerHelper;

public class ManejadorDePois {

	private static ManejadorDePois singleton = null;
	public List<POI> listaPois;
	private List<ComponenteExternoAdapter> adaptersComponentesExternos;

	private ManejadorDePois() {
		this.inicializarListaPois();
	}

	@SuppressWarnings("unchecked")
	private void inicializarListaPois() {
		listaPois = EntityManagerHelper.createQuery("from POI").getResultList();
	}

	public static ManejadorDePois getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDePois();
		}

		return singleton;
	}

	@SuppressWarnings("unchecked")
	public void setListaPois(List<POI> listaPoisNueva) {

		listaPois = listaPoisNueva;

		EntityManagerHelper.beginTransaction();

		List<POI> poisActuales = EntityManagerHelper.createQuery("from POI").getResultList();
		poisActuales.stream().forEach(poi -> EntityManagerHelper.remove(poi));

		listaPoisNueva.stream().forEach(poi -> EntityManagerHelper.persist(poi));

		EntityManagerHelper.commit();

	}

	public void setListaAdapters(List<ComponenteExternoAdapter> listaAdapters) {
		adaptersComponentesExternos = listaAdapters;
	}

	public void agregarPoi(POI poi) {
		if (this.estaEnLaLista(poi))
			actualizarPoi(poi);
		else {
			listaPois.add(poi);
			this.persistirPOI(poi);
		}

	}

	private void persistirPOI(POI poi) {
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.persist(poi);
		EntityManagerHelper.commit();
	}

	private void actualizarPoi(POI poiNuevo) {

		POI poiViejoLista = listaPois.stream().filter(unPoi -> unPoi.esIgualA(poiNuevo)).findFirst().get();

		listaPois.remove(poiViejoLista);
		listaPois.add(poiNuevo);

		EntityManagerHelper.beginTransaction();
		POI poiViejo = EntityManagerHelper.find(POI.class, poiViejoLista.getId());
		poiViejo.copiarEstado(poiNuevo);
		EntityManagerHelper.flush();
		EntityManagerHelper.commit();

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
		this.eliminarPoiDeBD(poi);
	}

	private void eliminarPoiDeBD(POI poi) {
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.remove(poi);
		EntityManagerHelper.commit();
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public Integer actualizarEtiquetasLocalesComerciales(String nombre, List<String> etiquetas) {

		EntityManagerHelper.beginTransaction();

		List<Comercio> comercios = EntityManagerHelper.createQuery("from Comercio where nombre = :nombre")
				.setParameter("nombre", nombre).getResultList();

		comercios.stream().forEach(comercio -> comercio.setEtiquetas(etiquetas));

		EntityManagerHelper.flush();
		EntityManagerHelper.commit();

		return comercios.size();

	}

	@SuppressWarnings("unchecked")
	public POI buscarPOI(String nombrePOI, Direccion direccionPOI) {
		// Si no encuentra ninguno tira IndexOutOfBoundsException

		return listaPois.stream().filter(poi -> poi.getNombre().equalsIgnoreCase(nombrePOI)
				&& poi.getDireccion().esLaMismaDireccionQue(direccionPOI)).collect(Collectors.toList()).get(0);
	}

}
