package POI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

public class CgpAdapter implements ComponenteExternoAdapter {
	ServicioExternoCGP servicioExternoCgp;

	private static List<Point> limitesComuna1 = new ArrayList<Point>() {
		{
			add(new Point(-34.599641, -58.392838));
			add(new Point(-34.599334, -58.386819));
			add(new Point(-34.591668, -58.387849));
			add(new Point(-34.579549, -58.376588));
			add(new Point(-34.579563, -58.380277));
			add(new Point(-34.577163, -58.385259));
			add(new Point(-34.575461, -58.382815));
			add(new Point(-34.570429, -58.391181));
			add(new Point(-34.569423, -58.390053));
			add(new Point(-34.568572, -58.381781));
			add(new Point(-34.583742, -58.359690));
			add(new Point(-34.588618, -58.359408));
			add(new Point(-34.590131, -58.367872));
			add(new Point(-34.596779, -58.363063));
			add(new Point(-34.595247, -58.357107));
			add(new Point(-34.602294, -58.347429));
			add(new Point(-34.609954, -58.341845));
			add(new Point(-34.615621, -58.339798));
			add(new Point(-34.617306, -58.348732));
			add(new Point(-34.618991, -58.348360));
			add(new Point(-34.618685, -58.338309));
			add(new Point(-34.622514, -58.337006));
			add(new Point(-34.622973, -58.340915));
			add(new Point(-34.626802, -58.336448));
			add(new Point(-34.632469, -58.349476));
			add(new Point(-34.625082, -58.360909));
			add(new Point(-34.625459, -58.368086));
			add(new Point(-34.627093, -58.368086));
			add(new Point(-34.629480, -58.370529));
			add(new Point(-34.626716, -58.370835));
			add(new Point(-34.626949, -58.374277));
			add(new Point(-34.632213, -58.383163));
			add(new Point(-34.633841, -58.390305));
		}
	};
	private static Comuna comuna1 = new Comuna(1, limitesComuna1);

	private static List<Point> limitesComuna2 = new ArrayList<Point>() {
		{
			add(new Point(-34.579634, -58.376988));
			add(new Point(-34.579846, -58.380250));
			add(new Point(-34.577726, -58.385399));
			add(new Point(-34.576383, -58.385314));
			add(new Point(-34.572143, -58.392953));
			add(new Point(-34.572253, -58.395231));
			add(new Point(-34.569973, -58.399207));
			add(new Point(-34.573201, -58.401736));
			add(new Point(-34.578249, -58.396354));
			add(new Point(-34.584502, -58.400385));
			add(new Point(-34.583429, -58.401988));
			add(new Point(-34.583594, -58.405895));
			add(new Point(-34.594233, -58.414309));
			add(new Point(-34.597778, -58.415812));
			add(new Point(-34.597943, -58.404392));
			add(new Point(-34.599263, -58.401888));
			add(new Point(-34.599180, -58.387062));
			add(new Point(-34.591676, -58.388064));
		}
	};
	private static Comuna comuna2 = new Comuna(2, limitesComuna2);

	private static List<Point> limitesComuna3 = new ArrayList<Point>() {
		{
			add(new Point(-34.627140, -58.391285));
			add(new Point(-34.599662, -58.393001));
			add(new Point(-34.599450, -58.402185));
			add(new Point(-34.598178, -58.404417));
			add(new Point(-34.598108, -58.411884));
			add(new Point(-34.608351, -58.414631));
			add(new Point(-34.614144, -58.412571));
			add(new Point(-34.630177, -58.411627));
		}
	};
	private static Comuna comuna3 = new Comuna(3, limitesComuna3);

	private static List<Point> limitesComuna4 = new ArrayList<Point>() {
		{
			add(new Point(-34.625272, -58.367778));
			add(new Point(-34.624849, -58.361083));
			add(new Point(-34.628804, -58.354903));
			add(new Point(-34.633041, -58.352843));
			add(new Point(-34.639114, -58.357822));
			add(new Point(-34.639821, -58.361426));
			add(new Point(-34.641657, -58.358337));
			add(new Point(-34.644764, -58.357650));
			add(new Point(-34.647870, -58.362628));
			add(new Point(-34.651118, -58.369666));
			add(new Point(-34.653660, -58.370181));
			add(new Point(-34.656343, -58.374129));
			add(new Point(-34.657614, -58.385974));
			add(new Point(-34.661850, -58.391982));
			add(new Point(-34.661568, -58.397647));
			add(new Point(-34.659591, -58.395759));
			add(new Point(-34.658179, -58.399535));
			add(new Point(-34.660720, -58.399364));
			add(new Point(-34.659026, -58.403312));
			add(new Point(-34.659591, -58.408462));
			add(new Point(-34.658461, -58.413611));
			add(new Point(-34.661426, -58.423740));
			add(new Point(-34.652813, -58.434554));
			add(new Point(-34.655496, -58.437816));
			add(new Point(-34.650271, -58.444854));
			add(new Point(-34.645328, -58.432151));
			add(new Point(-34.641657, -58.431808));
			add(new Point(-34.638267, -58.411037));
			add(new Point(-34.630499, -58.411208));
			add(new Point(-34.627532, -58.391296));
			add(new Point(-34.634171, -58.390609));
			add(new Point(-34.632052, -58.382026));
			add(new Point(-34.627109, -58.374473));
			add(new Point(-34.626967, -58.371383));
			add(new Point(-34.629510, -58.371039));
			add(new Point(-34.627250, -58.367778));
			add(new Point(-34.625272, -58.367606));
		}
	};
	private static Comuna comuna4 = new Comuna(4, limitesComuna4);

	private static List<Point> limitesComuna5 = new ArrayList<Point>() {
		{
			add(new Point(-34.598322, -58.412213));
			add(new Point(-34.602632, -58.432984));
			add(new Point(-34.639930, -58.423477));
			add(new Point(-34.637734, -58.411375));
		}
	};
	private static Comuna comuna5 = new Comuna(5, limitesComuna5);

	private static List<Point> limitesComuna6 = new ArrayList<Point>() {
		{
			add(new Point(-34.602846, -58.433272));
			add(new Point(-34.605884, -58.430783));
			add(new Point(-34.615491, -58.430096));
			add(new Point(-34.615141, -58.429269));
			add(new Point(-34.626904, -58.426846));
			add(new Point(-34.630557, -58.451676));
			add(new Point(-34.607393, -58.462635));
			add(new Point(-34.604568, -58.458386));
			add(new Point(-34.607797, -58.446210));
			add(new Point(-34.605241, -58.438773));
		}
	};
	private static Comuna comuna6 = new Comuna(6, limitesComuna6);

	private static List<Point> limitesComuna7 = new ArrayList<Point>() {
		{
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			
		}
	};
	private static Comuna comuna7 = new Comuna(7, limitesComuna7);

	private static List<Point> limitesComuna8 = new ArrayList<Point>() {
		{
			add(new Point(-34.626924, -58.426917));
			add(new Point(-34.639989, -58.423742));
			add(new Point(-34.641754, -58.432325));
			add(new Point(-34.645214, -58.432325));
			add(new Point(-34.650228, -58.445113));
			add(new Point(-34.650934, -58.444598));
			add(new Point(-34.650863, -58.453868));
			add(new Point(-34.656653, -58.460477));
			add(new Point(-34.651922, -58.466485));
			add(new Point(-34.646556, -58.462623));
			add(new Point(-34.641189, -58.469404));
			add(new Point(-34.622404, -58.477557));
			add(new Point(-34.614069, -58.459447));
			add(new Point(-34.630455, -58.451808));
		}
	};
	private static Comuna comuna8 = new Comuna(8, limitesComuna8);

	private static List<Point> limitesComuna9 = new ArrayList<Point>() {
		{
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
		}
	};
	private static Comuna comuna9 = new Comuna(9, limitesComuna9);

	private static List<Point> limitesComuna10 = new ArrayList<Point>() {
		{
			add(new Point(-34.611015, -58.529025));
			add(new Point(-34.615256, -58.531221));
			add(new Point(-34.634217, -58.529806));
			add(new Point(-34.634495, -58.510883));
			add(new Point(-34.639959, -58.509675));
			add(new Point(-34.645743, -58.502325));
			add(new Point(-34.643387, -58.497065));
			add(new Point(-34.644764, -58.495284));
			add(new Point(-34.636925, -58.478568));
			add(new Point(-34.638797, -58.476058));
			add(new Point(-34.636537, -58.471423));
			add(new Point(-34.622482, -58.477860));
			add(new Point(-34.624637, -58.482710));
			add(new Point(-34.614536, -58.495627));
			add(new Point(-34.609238, -58.500219));
			add(new Point(-34.616726, -58.513008));
			add(new Point(-34.620399, -58.516870));
		}
	};

	private static Comuna comuna10 = new Comuna(10, limitesComuna10);

	private static List<Point> limitesComuna11 = new ArrayList<Point>() {
		{
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
		}
	};
	private static Comuna comuna11 = new Comuna(11, limitesComuna11);

	private static List<Point> limitesComuna12 = new ArrayList<Point>() {
		{
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
		}
	};
	private static Comuna comuna12 = new Comuna(12, limitesComuna12);

	private static List<Point> limitesComuna13 = new ArrayList<Point>() {
		{
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
		}
	};
	private static Comuna comuna13 = new Comuna(13, limitesComuna13);

	private static List<Point> limitesComuna14 = new ArrayList<Point>() {
		{
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
		}
	};
	private static Comuna comuna14 = new Comuna(14, limitesComuna14);

	private static List<Point> limitesComuna15 = new ArrayList<Point>() {
		{
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
			add(new Point());
		}
	};
	private static Comuna comuna15 = new Comuna(15, limitesComuna15);

	private static List<Comuna> comunas = new ArrayList<Comuna>() {
		{
			add(comuna1);
			add(comuna2);
			add(comuna3);
			add(comuna4);
			add(comuna5);
			add(comuna6);
			add(comuna7);
			add(comuna8);
			add(comuna9);
			add(comuna10);
			add(comuna11);
			add(comuna12);
			add(comuna13);
			add(comuna14);
			add(comuna15);
		}
	};

	@Override
	public ArrayList<POI> buscarPoisExternos(String descripcion) {
		return this.adaptarCentrosDTO(servicioExternoCgp.buscar(descripcion));
	}

	private ArrayList<POI> adaptarCentrosDTO(List<CentroDTO> centrosDTO) {
		return centrosDTO.stream().map(centroDTO -> this.adaptarACGP(centroDTO)).collect(Collectors.toList());
	}

	private CGP adaptarACGP(CentroDTO centroDTO) {
		Integer numeroComuna = centroDTO.getNumeroComunaCentroDTO();
		String nombre = "CGP Comuna " + numeroComuna.toString();
		Comuna comuna = this.adaptarAComuna(numeroComuna);
		Direccion direccion = this.adaptarADireccion(centroDTO.getDomicilioCentroDTO());
		List<Servicio> servicios = this.adaptarServiciosCentroDTO(centroDTO.getServiciosDTO());

		return new CGP(servicios, comuna, posicion, nombre, direccion, etiquetas);
	}

	private List<Servicio> adaptarServiciosCentroDTO(List<ServicioDTO> serviciosDTO) {
		List<Servicio> servicios = serviciosDTO.stream().map(servicioDTO -> this.adaptarAServicio(servicioDTO))
				.collect(Collectors.toList());
		return servicios;
	}

	private Servicio adaptarAServicio(ServicioDTO servicioDTO) {
		String nombre = servicioDTO.getNombre();
		List<FranjaHoraria> horarios = this.adaptarAFranjasHorarias(servicioDTO.getRangoServicios());
		return new Servicio(nombre, horarios);
	}

	private List<FranjaHoraria> adaptarAFranjasHorarias(List<Integer> rangoServicios) {
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
