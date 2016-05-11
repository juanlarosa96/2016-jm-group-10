package POI;

import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public abstract class POI {
	
	private Point posicion;
	private String nombre;
	private Direccion direccion;
	private List<String> etiquetas;
	private List<FranjaHoraria> horarios;	
	

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
		return horarios.stream().anyMatch(unHorario -> unHorario.estaEnFranjaHoraria(fecha));
	}

	public Boolean estasCerca(Point unaPosicion) {
		
		return this.distanciaAPosicion(unaPosicion) <= this.condicionDeCercania();
		
	}

	private Double distanciaAPosicion(Point unaPosicion) {
		
		return posicion.distance(unaPosicion);
	}

	protected Double condicionDeCercania() {
		// devuelve la cantidad de kilometros maxima que considera cerca
		return 0.5;
	}

	public Boolean contiene(String descripcion) {
		return (descripcion.contains(nombre) || etiquetas.stream().anyMatch(palabra -> descripcion.contains(palabra)));
	}

	public Boolean estaDisponibleServicio(String servicio, DateTime momento) {
		return false;
	}

	public Point getPosicion() {
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

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	
	

}