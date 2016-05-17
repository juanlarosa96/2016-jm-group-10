package tpaPOIs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;

public class CgpAdapter implements ComponenteExternoAdapter {

	ServicioExternoCGP servicioExternoCgp;

	@Override
	public ArrayList<POI> buscarPoisExternos(String descripcion) {
		return this.adaptarCentrosDTO(servicioExternoCgp.buscar(descripcion));
	}

	private ArrayList<POI> adaptarCentrosDTO(List<CentroDTO> centrosDTO) {
		return (ArrayList<POI>) centrosDTO.stream().map(centroDTO -> this.adaptarACGP(centroDTO))
				.collect(Collectors.toList());
	}

	private POI adaptarACGP(CentroDTO centroDTO) {
		Integer numeroComuna = centroDTO.getNumeroComunaCentroDTO();
		String nombre = "CGP Comuna " + numeroComuna.toString();
		Comuna comuna = this.adaptarAComuna(numeroComuna);
		Direccion direccion = this.adaptarADireccion(centroDTO.getDomicilioCentroDTO());
		List<Servicio> servicios = this.adaptarServiciosCentroDTO(centroDTO.getServiciosDTO());
		List<String> etiquetas = new ArrayList<String>() {
			{
				add("CGP");
				add(numeroComuna.toString());
				add("Comuna");
			}
		};

		//no sabemos la posicion del cgp en coordenadas
		return new CGP(servicios, comuna, null /* posicion */, nombre, direccion, etiquetas);

	}

	private List<Servicio> adaptarServiciosCentroDTO(List<ServicioDTO> serviciosDTO) {
		List<Servicio> servicios = serviciosDTO.stream().map(servicioDTO -> this.adaptarAServicio(servicioDTO))
				.collect(Collectors.toList());
		return servicios;
	}

	private Servicio adaptarAServicio(ServicioDTO servicioDTO) {
		String nombre = servicioDTO.getNombre();
		List<FranjaHoraria> horarios = this.adaptarAFranjasHorarias(servicioDTO.getRangosHorarios());
		return new Servicio(nombre, horarios);
	}

	private List<FranjaHoraria> adaptarAFranjasHorarias(List<RangoServicioDTO> rangoServicios) {
		return rangoServicios.stream().map(rangoHorario -> this.adaptarAFranjaHoraria(rangoHorario))
				.collect(Collectors.toList());
	}

	private FranjaHoraria adaptarAFranjaHoraria(RangoServicioDTO rangoHorario) {
		Integer dia = rangoHorario.getDia();
		Integer horaDesde = rangoHorario.getHoraDesde();
		Integer minutoDesde = rangoHorario.getMinutoDesde();
		Integer horaHasta = rangoHorario.getHoraHasta();
		Integer minutoHasta = rangoHorario.getMinutoHasta();

		return new FranjaHoraria(dia, new LocalTime(horaDesde, minutoDesde), new LocalTime(horaHasta, minutoHasta));
	}

	private Comuna adaptarAComuna(Integer numeroComuna) {

		return new Comuna(numeroComuna, null);
	}

	private Direccion adaptarADireccion(String direccionCentroDTO) {
		String[] direccion = direccionCentroDTO.split(" ", 2);
		String calle = direccion[0];
		Integer altura = Integer.parseInt(direccion[1]);
		return new Direccion(calle, altura, null, null, null, null, null, "CABA", null, null, "Argentina");
	}

}
