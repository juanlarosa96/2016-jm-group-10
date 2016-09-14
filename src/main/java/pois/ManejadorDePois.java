package pois;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;
import org.omg.CORBA.PERSIST_STORE;

import adapters.ComponenteExternoAdapter;
import tests.EntityManagerHelper;


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
		unaListaDePois.stream().forEach(poi -> EntityManagerHelper.getEntityManager().persist(poi));
	}


	public void setListaAdapters(List<ComponenteExternoAdapter> listaAdapters) {
		adaptersComponentesExternos = listaAdapters;
	}

	public void agregarPoi(POI poi) {
		if(this.estaEnLaLista(poi))
			actualizarPoi(poi);
		else{
			listaPois.add(poi);
			EntityManagerHelper.getEntityManager().persist(poi);
	}
		
	}

	private void actualizarPoi(POI poiNuevo) {
		
		POI poiViejoLista = listaPois.stream().filter(unPoi -> unPoi.esIgualA(poiNuevo)).findFirst().get();
		
		this.eliminarPOI(poiViejoLista);
		
		listaPois.add(poiNuevo);
		
		EntityManager em = EntityManagerHelper.getEntityManager(); 
 		POI poiViejo = em.find(POI.class, poiNuevo.getId());
 		poiViejo.copiarEstado(poiNuevo);
		em.flush();
		
				
	}

	private boolean estaEnLaLista(POI poiBuscado) {
				return listaPois.stream().anyMatch(unPoi -> poiBuscado.esIgualA(unPoi));
		/*POI poi =  EntityManagerHelper.getEntityManager().find(POI.class, poiBuscado.getId());
	if(poi != null){
		return true;
	}
	return false;

	}
	*/}

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
		listaDePois.stream().forEach(poi -> EntityManagerHelper.getEntityManager().persist(poi));
	}

	public Boolean poiDisponible(POI poi, DateTime momento) {
		return poi.estaDisponible(momento);
	}

	public void eliminarPOI(POI poi) {
		listaPois.remove(poi);
		EntityManagerHelper.getEntityManager().remove(poi);
	}

	@SuppressWarnings("unchecked")
	public List<POI> buscarPOIs(String descripcion) {
		this.consultarPoisExternos(descripcion);
		List<POI> listaPois = EntityManagerHelper.getEntityManager().createQuery("from pois").getResultList();
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
		EntityManager em = EntityManagerHelper.getEntityManager(); 
		List<POI> lista = em.createQuery("from comercios where nombre = :nombre").setParameter("nombre", nombre).getResultList();			
		lista.stream().forEach(comercio -> comercio.setEtiquetas(etiquetas));
		em.flush();
		return lista.size();

	}

	@SuppressWarnings("unchecked")
	public POI buscarPOI(String nombrePOI, Direccion direccionPOI) {
		return listaPois.stream().filter(
				poi -> poi.getNombre().equalsIgnoreCase(nombrePOI) && poi.getDireccion().esLaMismaDireccionQue(direccionPOI))
				.collect(Collectors.toList()).get(0);
		/*List<POI> listaPois = EntityManagerHelper.getEntityManager().createQuery("from pois where nombre = :nombrePOI").setParameter("nombrePOI", nombrePOI).getResultList();
		return listaPois.stream().filter(poi -> poi.getDireccion().esLaMismaDireccionQue(direccionPOI)).collect(Collectors.toList()).get(0);
		*/
		// Si no encuentra ninguno tira IndexOutOfBoundsException
	}

}
