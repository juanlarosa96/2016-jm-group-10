package pois;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class POIDTO {

	private Posicion posicion;
	private String nombre;
	private DireccionDTO direccionDTO;
	private List<String> etiquetas;
	private List<FranjaHoraria> horarios;
	private List<ServicioDTO> serviciosDTO;
	private Comuna comuna;
	private RubroDTO rubroDTO;
	private Integer lineaColectivo;

	@SuppressWarnings("unused")
	private POIDTO() {
	}

	public POIDTO(String nombre, Posicion posicion, Direccion direccion, List<String> etiquetas,
			List<FranjaHoraria> horarios) {

		this.nombre = nombre;
		this.setPosicion(posicion);
		this.setDireccion(direccion);
		this.setEtiquetas(etiquetas);
		this.setHorarios(horarios);
	}

	private void setEtiquetas(List<String> listaEtiquetas) {
		this.etiquetas = new ArrayList<String>();
		this.etiquetas.addAll(listaEtiquetas);
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion.clone();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(Direccion direccion) {
		this.direccionDTO = direccion.dameTuDTO();
	}

	public void setHorarios(List<FranjaHoraria> horarios) {
		this.horarios = FranjaHoraria.clonarListaHorarios(horarios);
	}

	public void setServicios(List<Servicio> servicios) {
		this.serviciosDTO = this.convertirAServiciosDTO(servicios);
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna.clone();
	}

	public void setRubro(Rubro rubro) {
		this.rubroDTO = rubro.dameTuDTO();
	}

	public void setLinea(Integer linea) {
		this.lineaColectivo = linea;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public DireccionDTO getDireccionDTO() {
		return direccionDTO;
	}

	public List<String> getEtiquetas() {
		return etiquetas;
	}

	public List<FranjaHoraria> getHorarios() {
		return horarios;
	}

	public List<ServicioDTO> getServiciosDTO() {
		return serviciosDTO;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public RubroDTO getRubroDTO() {
		return rubroDTO;
	}

	public Integer getLinea() {
		return lineaColectivo;
	}

	private List<ServicioDTO> convertirAServiciosDTO(List<Servicio> listaServicios) {
		return listaServicios.stream().map(servicio -> servicio.dameTuDTO()).collect(Collectors.toList());
	}

}
