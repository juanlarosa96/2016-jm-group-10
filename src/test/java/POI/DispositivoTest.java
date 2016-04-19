package POI;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class DispositivoTest {

	private Dispositivo dispositivo;
	private Point posicionDispositivo;

	private CGP cgpComuna10;
	private Direccion direccionCgpComuna10;
	private Point posicionCgpComuna10;
	private List<Servicio> serviciosCGP;
	private List<String> etiquetasCGP;
	
	private Servicio rentas;
	private Servicio asesoramientoJuridico;
	private Servicio ecobici;
	
	private Comuna comuna10;
	private List<Point> limitesComuna10;
	
	private Banco bancoProvincia;
	private Direccion direccionBancoProvincia;
	private Point posicionBancoProvincia;
	private List<String> etiquetasBancoProvincia;
	
	private Comercio elHalcon;
	private Direccion direccionElHalcon;
	private Point posicionElHalcon;
	private Rubro restaurant;
	private List<FranjaHoraria> horariosElHalcon;
	private List<String> etiquetasElHalcon;
	
	private ParadaColectivo parada114;	
	private Direccion direccionParada114;	
	private Point posicionParada114;
	private List<String> etiquetasParada114;
	

	private List<POI> listaPoisDispositivo;
	private DateTime lunes4abril10am;
	private DateTime martes5abril2am;
	private List<FranjaHoraria> horariosRentas;
	private List<FranjaHoraria> horariosAsesoramientoJuridico;
	private List<FranjaHoraria> horariosEcobici;
	
	@Before
	public void init() {
		

		lunes4abril10am = new DateTime(2016,4,4,10,0);
		martes5abril2am = new DateTime(2016,4,5,2,30);

		posicionDispositivo = new Point(-34.631402, -58.488060);
		dispositivo = new Dispositivo(posicionDispositivo);
		
		posicionParada114 = new Point(-34.631997, -58.484737);
		direccionParada114 = new Direccion("Av. Segurola", 230, "Bacacay", "Bogota", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		etiquetasParada114 = new ArrayList<String>(){
			{
				add("Parada");
				add("parada");
				add("114");
				add("colectivo");
			}
		};
		parada114 = new ParadaColectivo(114, posicionParada114, "114", direccionParada114, etiquetasParada114);

		posicionBancoProvincia = new Point(-34.6327475, -58.4851585);
		direccionBancoProvincia = new Direccion("Av. Rivadavia", 8468, "Benedetti", "Mariano Acosta", null, null, 1407,
				"CABA", "Floresta", "CABA", "Argentina");
		etiquetasBancoProvincia = new ArrayList<String>() {
			{
				add("banco");
				add("provincia");
				add("depositos");
				add("extracciones");
				add("cajero");
			}
		};
		bancoProvincia = new Banco(posicionBancoProvincia, "Banco Provincia", direccionBancoProvincia,
				etiquetasBancoProvincia);
		
		posicionCgpComuna10 = new Point(-34.6369004, -58.4959096);
		direccionCgpComuna10 = new Direccion("Bacacay", 3968, "Campana", "Concordia", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		
		horariosRentas= new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(9,30), new LocalTime(14,0)));
				add(new FranjaHoraria(2, new LocalTime(9,30), new LocalTime(14,0)));
				add(new FranjaHoraria(3, new LocalTime(9,30), new LocalTime(14,0)));
				add(new FranjaHoraria(4, new LocalTime(9,30), new LocalTime(14,0)));
				add(new FranjaHoraria(5, new LocalTime(9,30), new LocalTime(14,0)));
			}
		};
		
		horariosAsesoramientoJuridico= new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(2, new LocalTime(9,0), new LocalTime(16,0)));				
			}
		};
		
		horariosEcobici= new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(10,0), new LocalTime(15,0)));
				add(new FranjaHoraria(2, new LocalTime(10,0), new LocalTime(15,0)));
				add(new FranjaHoraria(3, new LocalTime(10,0), new LocalTime(15,0)));
				add(new FranjaHoraria(4, new LocalTime(10,0), new LocalTime(15,0)));
				add(new FranjaHoraria(5, new LocalTime(10,0), new LocalTime(15,0)));				
			}
		};
		
		rentas=new Servicio("Rentas",horariosRentas);
		asesoramientoJuridico= new Servicio("Asesoramiento Juridico", horariosAsesoramientoJuridico);
		ecobici= new Servicio ("Ecobici",horariosEcobici);
		

		serviciosCGP = new ArrayList<Servicio>() {
			{
				add(rentas);
				add(asesoramientoJuridico);
				add(ecobici);
			}
		};
		etiquetasCGP = new ArrayList<String>() {
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
		
		cgpComuna10 = new CGP(serviciosCGP, comuna10, posicionCgpComuna10, "CGP Comuna 10", direccionCgpComuna10,
				etiquetasCGP);
		
		posicionElHalcon = new Point(-34.6327106, -58.4877209);
		direccionElHalcon = new Direccion("Av. Rivadavia", 8451, "Mercedes", "Av. Segurola", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		horariosElHalcon = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(9,30), new LocalTime(13,0)));
				add(new FranjaHoraria(1, new LocalTime(14,30), new LocalTime(19,0)));
				add(new FranjaHoraria(2, new LocalTime(9,30), new LocalTime(13,0)));
				add(new FranjaHoraria(2, new LocalTime(14,30), new LocalTime(19,0)));
				add(new FranjaHoraria(3, new LocalTime(9,30), new LocalTime(13,0)));
				add(new FranjaHoraria(3, new LocalTime(14,30), new LocalTime(19,0)));
				add(new FranjaHoraria(4, new LocalTime(9,30), new LocalTime(13,0)));
				add(new FranjaHoraria(4, new LocalTime(14,30), new LocalTime(19,0)));
				add(new FranjaHoraria(5, new LocalTime(9,30), new LocalTime(13,0)));
				add(new FranjaHoraria(5, new LocalTime(14,30), new LocalTime(19,0)));
				add(new FranjaHoraria(6, new LocalTime(10,0), new LocalTime(14,0)));
				
			}
		};
		etiquetasElHalcon = new ArrayList<String>() {
			{
				add("restaurant");
				add("almuerzo");
				add("cena");
				add("comida");
				add("el");
				add("halcon");
				add("efectivo");
				add("tarjeta");
				add("credito");
				add("debito");
			}
		};
		
		restaurant= new Rubro("Restaurant",0.3);
		elHalcon = new Comercio(restaurant, horariosElHalcon, posicionElHalcon, "El Halcon", direccionElHalcon, etiquetasElHalcon);
	
		listaPoisDispositivo = new ArrayList<POI>(){
			{
				add(parada114);
				add(elHalcon);
				add(bancoProvincia);
				add(cgpComuna10);
			}
		};
		
		Dispositivo.setListaPois(listaPoisDispositivo);
	}
	
	@Test
	public void BancoProvinciaEstaDisponible(){
		Assert.assertTrue(bancoProvincia.estaDisponible(lunes4abril10am));
	}
	
	@Test
	public void BancoProvinciaNoEstaDisponible(){
		Assert.assertFalse(bancoProvincia.estaDisponible(martes5abril2am));
	}
	
	@Test
	public void ColectivoEstaDisponible(){
		Assert.assertTrue(parada114.estaDisponible(martes5abril2am));
	}
	
	
	@Test
	public void ParadaColectivo114estaEnListaPois(){		
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(parada114));
	}
	
	@Test
	public void MacowinsNoEstaEnListaPois(){
		Assert.assertTrue(dispositivo.buscarPOIs("macowins").isEmpty());
	}
	
}
