package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.uqbar.geodds.Point;

 public class FixtureCGP {
	
	private static CGP cgp;
	private static Direccion direccionCgpComuna10;
	private static Point posicionCgpComuna10;
	private static List<Servicio> serviciosCGP10;
	private static List<String> etiquetasCGP10;
	private static Servicio rentas;
	private static Servicio asesoramientoJuridico;
	private static Servicio ecobici;

	private static Comuna comuna10;
	private static List<Point> limitesComuna10;

	
	private static List<FranjaHoraria> horariosRentas;
	private static List<FranjaHoraria> horariosAsesoramientoJuridico;
	private static List<FranjaHoraria> horariosEcobici;

	
	public static CGP dameCGPValido(){
		
		comuna10 = FixtureComuna.dameComunaValida();

		horariosRentas = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(9, 30), new LocalTime(14, 0)));
				add(new FranjaHoraria(2, new LocalTime(9, 30), new LocalTime(14, 0)));
				add(new FranjaHoraria(3, new LocalTime(9, 30), new LocalTime(14, 0)));
				add(new FranjaHoraria(4, new LocalTime(9, 30), new LocalTime(14, 0)));
				add(new FranjaHoraria(5, new LocalTime(9, 30), new LocalTime(14, 0)));
			}
		};

		horariosAsesoramientoJuridico = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(2, new LocalTime(9, 0), new LocalTime(16, 0)));
			}
		};

		horariosEcobici = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(10, 0), new LocalTime(15, 0)));
				add(new FranjaHoraria(2, new LocalTime(10, 0), new LocalTime(15, 0)));
				add(new FranjaHoraria(3, new LocalTime(10, 0), new LocalTime(15, 0)));
				add(new FranjaHoraria(4, new LocalTime(10, 0), new LocalTime(15, 0)));
				add(new FranjaHoraria(5, new LocalTime(10, 0), new LocalTime(15, 0)));
			}
		};

		rentas = new Servicio("Rentas", horariosRentas);
		asesoramientoJuridico = new Servicio("asesoramiento juridico", horariosAsesoramientoJuridico);
		ecobici = new Servicio("Ecobici", horariosEcobici);

		serviciosCGP10 = new ArrayList<Servicio>() {
			{
				add(rentas);
				add(asesoramientoJuridico);
				add(ecobici);
			}
		};
		etiquetasCGP10 = new ArrayList<String>() {
			{
				add("CGP");
				add("Cgp");
				add("cgp");
				add("rentas");
				add("asesoramiento");
				add("juridico");
				add("ecobici");
			}
		};
		

		cgp = new CGP(serviciosCGP10, comuna10, posicionCgpComuna10, "CGP Comuna 10", direccionCgpComuna10,
				etiquetasCGP10);
		return cgp;		
		
	}
	
	public static DateTime dameHorarioValido(){
		return new DateTime(2016, 4, 4, 10, 0);
		
	}
	public static DateTime dameHorarioNoValido(){
		return new DateTime(2016, 4, 5, 2, 30);
	}
	
	public static Point damePosicionNoCercana(){
		return new Point(-34.6184994, -58.4368164);		
	}
	
	public static Point damePosicionCercana(){
		return new Point(-34.6327475, -58.4851585);		
	}
}
