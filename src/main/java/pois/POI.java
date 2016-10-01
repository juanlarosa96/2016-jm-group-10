package pois;

import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;

import herramientas.ManejadorDeFechas;
import herramientas.ManejadorDeStrings;

@Entity
@Table(name = "pois")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class POI {

	@Id
	@GeneratedValue
	private Integer id;

	@Embedded
	private Posicion posicion;

	private String nombre;

	@Embedded
	private Direccion direccion;

	@ElementCollection
	private List<String> etiquetas;

	@CollectionTable(name = "franjas_horarias_pois")
	@ElementCollection
	private List<FranjaHoraria> horarios;

	// -------------------------------------------

	public Integer getId() {
		return id;
	}

	public Boolean distanciaAPoiMenorA(Double distancia, POI poi) {

		return distanciaAPoi(poi) < distancia;
	}

	public Double distanciaAPoi(POI poi) {
		return this.distanciaAPosicion(poi.getPosicion());
	}

	public Boolean esValido() {

		return (posicion != null && nombre != null && direccion != null);

	}

	public Boolean estaDisponible(DateTime fecha) {
		return horarios.stream().anyMatch(franjaHoraria -> ManejadorDeFechas.estaEnFranjaHoraria(fecha, franjaHoraria));
	}

	public Boolean estasCerca(Posicion unaPosicion) {

		return this.distanciaAPosicion(unaPosicion) <= this.condicionDeCercania();

	}

	private Double distanciaAPosicion(Posicion unaPosicion) {

		return posicion.distance(unaPosicion);
	}

	protected Double condicionDeCercania() {
		// devuelve la cantidad de kilometros maxima que considera cerca
		return 0.5;
	}

	public Boolean contiene(String descripcion) {
		return ManejadorDeStrings.estaIncluido(nombre, descripcion)
				|| etiquetas.stream().anyMatch(etiqueta -> ManejadorDeStrings.estaIncluido(etiqueta, descripcion))
				|| this.condicionDeBusqueda(descripcion);
	}

	public Boolean condicionDeBusqueda(String descripcion) {
		return false;
	}

	public Boolean estaDisponibleServicio(String servicio, DateTime momento) {
		return false;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public List<FranjaHoraria> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<FranjaHoraria> horarios) {
		this.horarios = horarios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public List<String> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public Boolean esIgualA(POI poi) {

		return this.getPosicion().equals(poi.getPosicion());
	}

	public void copiarEstado(POI poiNuevo) {
		this.setDireccion(poiNuevo.getDireccion());
		this.setEtiquetas(poiNuevo.getEtiquetas());
		this.setHorarios(poiNuevo.getHorarios());
		this.setNombre(poiNuevo.getNombre());
		this.setPosicion(poiNuevo.getPosicion());
	}

	public POIDTO dameTuDTO() {
		POIDTO poiDTO = new POIDTO(nombre, posicion, direccion, etiquetas, horarios);
		this.agregarDatosEspecificosDelPOI(poiDTO);
		return poiDTO;
	}

	abstract protected void agregarDatosEspecificosDelPOI(POIDTO poiDto);

}