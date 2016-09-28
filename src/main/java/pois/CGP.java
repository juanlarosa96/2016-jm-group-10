package pois;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.joda.time.DateTime;

@Table(name = "cgps")
@Entity
public class CGP extends POI {
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Servicio> servicios;
	
	@Embedded
	private Comuna comuna;
	
	@SuppressWarnings("unused")
	private CGP(){}
	
	public CGP(List<Servicio> servicios, Comuna comuna, Posicion posicion, String nombre, Direccion direccion,
			List<String> etiquetas) {
		this.servicios = servicios;
		this.comuna = comuna;
		this.setPosicion(posicion);
		this.setNombre(nombre);
		this.setDireccion(direccion);
		this.setEtiquetas(etiquetas);
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

	@Override
	public Boolean estaDisponibleServicio(String nombreServicio, DateTime fecha) {
		Servicio servicio = buscarServicio(nombreServicio);

		if (servicio != null)
			return servicio.estaDisponible(fecha);
		else {
			System.out.println("No existe el servicio en el CGP");
			return false;
		}

	}

	private Servicio buscarServicio(String nombreServicio) {

		List<Servicio> listaServicios = servicios.stream().filter(servicio -> servicio.nombreSimilarA(nombreServicio))
				.collect(Collectors.toList());
		if (listaServicios.isEmpty()) {
			return null;
		} else {
			return listaServicios.get(0);

		}

	}

	@Override
	public Boolean estaDisponible(DateTime fecha) { 

		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(fecha));
	}

	@Override
	public Boolean estasCerca(Posicion unaPosicion) {
		return comuna.incluyeA(unaPosicion);
	}
	@Override
	public Boolean condicionDeBusqueda(String descripcion) {
		return servicios.stream().anyMatch(servicio -> servicio.nombreSimilarA(descripcion));
	}
	
	@Override
	public Boolean esIgualA(POI otroPoi) {
		return this.getDireccion().esLaMismaDireccionQue(otroPoi.getDireccion());		
		
	}

	public Integer dameNumeroComuna() {
		
		return this.comuna.getNumero();
	}
	
	@Override
	public List<FranjaHoraria> getHorarios(){
		return this.servicios.stream().map(servicio -> servicio.getHorarios())
				.flatMap(listaHorarios -> listaHorarios.stream()).collect(Collectors.toList());
	}
	
	public List<String> getNombreServicios(){
		return this.servicios.stream().map(servicio -> servicio.getNombre()).collect(Collectors.toList());
	}

	private POIDTO agregarDatosEspecificosDelPOI(POIDTO poiDto) {
		poiDto.setComuna(this.getComuna());
		poiDto.setServicios(this.getServicios());
		return poiDto;
	}
	
}
