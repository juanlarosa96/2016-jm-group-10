package pois;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;


@Embedded
public class POIDTO {

	private Posicion posicion;
	private String nombre;
	private DireccionDTO direccion;
	private List<String> etiquetas;
	private List<FranjaHoraria> horarios;
	private List<Servicio> servicios;
	private Comuna comuna;
	private RubroDTO rubro;
	private Integer linea;
	
	@SuppressWarnings("unused")
	private POIDTO(){}
	
	public POIDTO(String nombre, Posicion posicion, Direccion direccion, List<String> etiquetas, List<FranjaHoraria> horarios){
		this.nombre = nombre;
		this.posicion = posicion;
		this.direccion = this.convertirADireccionDTO(direccion);
		this.etiquetas = etiquetas;
		this.horarios = horarios;
	}
	
	private DireccionDTO convertirADireccionDTO(Direccion direccion) {
		return new DireccionDTO(direccion.getCalle(), direccion.getAltura(), direccion.getEntreCalle1(),
				direccion.getEntreCalle2(), direccion.getPiso(), direccion.getDepartamento(),
				direccion.getCodigoPostal(), direccion.getLocalidad(), direccion.getBarrio(), direccion.getProvincia(),
				direccion.getPais());
	}

	private RubroDTO convertirARubroDTO(Rubro rubro) {
		return new RubroDTO(rubro.getNombreRubro(),rubro.getCondicionDeCercania());
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = this.convertirADireccionDTO(direccion);
	}
	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}
	public void setHorarios(List<FranjaHoraria> horarios) {
		this.horarios = horarios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}
	public void setRubro(Rubro rubro) {
		this.rubro = this.convertirARubroDTO(rubro);
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public Posicion getPosicion() {
		return posicion;
	}
	public String getNombre() {
		return nombre;
	}
	public DireccionDTO getDireccion() {
		return direccion;
	}
	public List<String> getEtiquetas() {
		return etiquetas;
	}
	public List<FranjaHoraria> getHorarios() {
		return horarios;
	}
	public List<Servicio> getServicios() {
		return servicios;
	}
	public Comuna getComuna() {
		return comuna;
	}
	public RubroDTO getRubro() {
		return rubro;
	}
	public Integer getLinea() {
		return linea;
	}

}
