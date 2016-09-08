package pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import eventosBusqueda.ResultadoBusqueda;
import eventosBusqueda.InteresadoEnBusquedas;
import herramientas.ManejadorDeFechas;

@Table(name = "dispositivos")
@Entity
public class Dispositivo {

	@Id
	@GeneratedValue
	private Integer id;

	private String nombre;

	// Averiguar como convertir posicion
	private Point posicion;

	@Transient
	private ManejadorDePois manejadorDePois;

	@Transient
	private List<InteresadoEnBusquedas> observers;
	
	@SuppressWarnings("unused")
	private Dispositivo(){}

	public Dispositivo(String unNombre, Point unaPosicion) {
		this.nombre = unNombre;
		this.posicion = unaPosicion;
		this.manejadorDePois = ManejadorDePois.getInstance();
		this.observers = new ArrayList<InteresadoEnBusquedas>();
	}

	public List<InteresadoEnBusquedas> getObservers() {
		return observers;
	}

	public Boolean estoyCercaDe(POI poi) {
		return poi.estasCerca(this.posicion);
	}

	public List<POI> buscarPOIs(String descripcion) {
		DateTime inicio = DateTime.now();

		List<POI> listaPoisEncontrados = manejadorDePois.buscarPOIs(descripcion);

		DateTime fin = DateTime.now();

		Double demoraEnSegundos = ManejadorDeFechas.obtenerDuracionIntervaloEnSegundos(inicio, fin);

		ResultadoBusqueda unaBusqueda = new ResultadoBusqueda(this.nombre, listaPoisEncontrados, DateTime.now(),
				demoraEnSegundos, descripcion);

		this.notificarBusqueda(unaBusqueda);

		return listaPoisEncontrados;
	}

	private void notificarBusqueda(ResultadoBusqueda unaBusqueda) {
		observers.stream().forEach(observer -> observer.notificarBusqueda(unaBusqueda));

	}

	public void agregarInteresadoEnBusquedas(InteresadoEnBusquedas unInteresado) {
		observers.add(unInteresado);
	}

	public void eliminarInteresadoEnBusquedas(InteresadoEnBusquedas unInteresado) {
		observers.remove(unInteresado);
	}

	public Point getPosicion() {
		return posicion;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

}
