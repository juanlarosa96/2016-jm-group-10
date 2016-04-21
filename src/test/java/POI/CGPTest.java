package POI;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;


public class CGPTest {


	private CGP cgpComuna10;
	private Direccion direccionCgpComuna10;
	private Point posicionCgpComuna10;
	private List<Servicio> serviciosCGP10;
	private List<String> etiquetasCGP10;

	private Servicio rentas;
	private Servicio asesoramientoJuridico;
	private Servicio ecobici;

	private Comuna comuna10;
	private List<Point> limitesComuna10;

	private DateTime lunes4abril10am;
	private DateTime martes5abril2am;
	private List<FranjaHoraria> horariosRentas;
	private List<FranjaHoraria> horariosAsesoramientoJuridico;
	private List<FranjaHoraria> horariosEcobici;
	
	private Point posicion1;
	private Point posicion3;
	

	
	
	
	@Before
	public void init() {

		lunes4abril10am = new DateTime(2016, 4, 4, 10, 0);
		martes5abril2am = new DateTime(2016, 4, 5, 2, 30);



		posicionCgpComuna10 = new Point(-34.6369004, -58.4959096);
		direccionCgpComuna10 = new Direccion("Bacacay", 3968, "Campana", "Concordia", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");

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

		limitesComuna10 = new ArrayList<Point>() {
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

		comuna10 = new Comuna(10, limitesComuna10);

		cgpComuna10 = new CGP(serviciosCGP10, comuna10, posicionCgpComuna10, "CGP Comuna 10", direccionCgpComuna10,
				etiquetasCGP10);
		
		
		posicion1 = new Point(-34.6184994, -58.4368164);
		
		posicion3 = new Point(-34.6327475, -58.4851585);	
	
	}
	
	@Test
	public void preguntoACGPSiEstaDisponibleYdiceQueSi() {
		Assert.assertTrue(cgpComuna10.estaDisponible(lunes4abril10am));
	}
	
	@Test
	public void preguntoACGPSiEstaDisponibleYdiceQueNo() {
		Assert.assertFalse(cgpComuna10.estaDisponible(martes5abril2am));
	}
	
	@Test
	public void buscarServicioInexistente(){
		Assert.assertFalse(cgpComuna10.estaDisponibleServicio("Servicio Inexistente", martes5abril2am));
		
	}
	
	@Test
	public void CgpComuna10NoEstaCercaDeMacowins(){
		Assert.assertFalse(cgpComuna10.estasCerca(posicion1));		
	}
	
	@Test
	public void CgpComuna10EstaCercaDeBancoProvincia(){
		Assert.assertTrue(cgpComuna10.estasCerca(posicion3));		
	}
	
	@Test
	public void servicioRentasNoEstaDisponibleMartesALas2am(){
		Assert.assertFalse(cgpComuna10.estaDisponibleServicio("Rentas", martes5abril2am));	
	}
	
	@Test
	public void servicioEcobiciEstaDisponibleLunesALas10am(){
		Assert.assertTrue(cgpComuna10.estaDisponibleServicio("Ecobici", lunes4abril10am));
	
	}
}
