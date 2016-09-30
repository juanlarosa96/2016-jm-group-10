package pois;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.joda.time.DateTime;

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

	@Embedded
	private Posicion posicion;

	@Transient
	private ManejadorDePois manejadorDePois;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<InteresadoEnBusquedas> observers;

	// ---------------------

	public Integer getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private Dispositivo() {
	}

	public Dispositivo(String unNombre, Posicion unaPosicion) {
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

		ResultadoBusqueda unaBusqueda = this.crearResultadoBusqueda(listaPoisEncontrados, demoraEnSegundos,
				descripcion);

		this.notificarBusqueda(unaBusqueda);

		return listaPoisEncontrados;
	}

	private ResultadoBusqueda crearResultadoBusqueda(List<POI> listaPoisEncontrados, Double demoraEnSegundos,
			String descripcion) {
		List<POIDTO> listaPoisParaResultadoBusqueda = listaPoisEncontrados.stream().map(poi -> poi.dameTuDTO())
				.collect(Collectors.toList());

		return new ResultadoBusqueda(this.nombre, listaPoisParaResultadoBusqueda, DateTime.now(), demoraEnSegundos,
				descripcion);
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

	public Posicion getPosicion() {
		return posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

}
