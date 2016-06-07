package tpaPOIs;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Dispositivo {
	private String nombre;
	private Point posicion;
	private ManejadorDePois manejadorDePois;
	private List<InteresadoEnBusquedas> observers;

	public Dispositivo(String unNombre, Point unaPosicion) {
		this.nombre = unNombre;
		this.posicion = unaPosicion;
		this.manejadorDePois = ManejadorDePois.getInstance();
	}

	public Boolean estoyCercaDe(POI poi) {
		return poi.estasCerca(this.posicion);
	}

	public List<POI> buscarPOIs(String descripcion) {
		DateTime inicio = DateTime.now();
		
		List<POI> listaPoisEncontrados = manejadorDePois.buscarPOIs(descripcion);
		
		DateTime fin = DateTime.now();
		
		Double demoraEnSegundos = ManejadorDeFechas.obtenerDuracionIntervaloEnSegundos(inicio,fin);
		
		Busqueda unaBusqueda = new Busqueda(nombre, listaPoisEncontrados.size(), DateTime.now(), demoraEnSegundos,
				descripcion);
		observers.stream().forEach(observer -> observer.notificarBusqueda(unaBusqueda));
		return listaPoisEncontrados;
	}
}




















