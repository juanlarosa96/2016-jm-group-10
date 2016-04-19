package POI;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import org.uqbar.geodds.Point;

public class TestPOI {

	private ParadaColectivo parada132;
	private List<String> etiquetas132;
	private Point posicion132;
	private Direccion direccion132;

	private Banco bancoCredicoop;
	private Point posicionCredicoop;
	private Direccion direccionCredicoop;
	private List<String> etiquetasCredicoop;

	private Comercio macowins;
	private Rubro localDeRopa;
	private Point posicionMacowins;
	private Direccion direccionMacowins;
	private List<String> etiquetasMacowins;
	
	private CGP cgpComuna10;
	private Comuna comuna10;	
	private Servicio rentas;
	private Servicio asesoramientoJuridico;
	private Servicio ecobici;
	private Direccion direccionCgpComuna10;	
	private Point posicionCgpComuna10;	
	private List<Servicio> serviciosCGP;
	private List<String> etiquetasCGP;
	private List<Point> limitesComuna10;
	private Point posicionBancoProvincia;
	private Direccion direccionBancoProvincia;
	private ArrayList<String> etiquetasBancoProvincia;
	private Banco bancoProvincia;

	@Before
	public void init() {
		etiquetas132 = new ArrayList<String>() {
			{
				add("parada");
				add("colectivo");
				add("132");
			}
		};
		posicion132 = new Point(-34.6184929, -58.4297692);
		direccion132 = new Direccion("Rosario", 50, "Av La Plata", "Senillosa", null, null, 1424, "CABA", "Caballito",
				"Buenos Aires", "Argentina");
		parada132 = new ParadaColectivo(132, posicion132, "Parada 132", direccion132, etiquetas132);

		etiquetasCredicoop = new ArrayList<String>() {
			{
				add("banco");
				add("credicoop");
				add("depositos");
				add("extracciones");
				add("cajero");
			}
		};
		posicionCredicoop = new Point(-34.6156067, -58.4299701);
		direccionCredicoop = new Direccion("Av Rivadavia", 4600, "Senillosa", "Av La Plata", null, null, 1424, "CABA",
				"Caballito", "Buenos Aires", "Argentina");
		bancoCredicoop = new Banco(posicionCredicoop, "Banco Credicoop", direccionCredicoop, etiquetasCredicoop);

		localDeRopa = new Rubro("Local de Ropa", 0.9);
		posicionMacowins = new Point(-34.6184994, -58.4368164);
		direccionMacowins = new Direccion("Av. Acoyte", 56, "Av. Rivadavia", "Yerbal", null, null, 1424, "CABA",
				"Caballito", "Buenos Aires", "Argentina");
		etiquetasMacowins= new ArrayList<String>(){{add("local");add("ropa");add("macowins");}};
		macowins = new Comercio(localDeRopa, null, posicionMacowins, "Macowins", direccionMacowins, etiquetasMacowins);
		
		
		
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
		
		direccionCgpComuna10 = new Direccion("Bacacay", 3968, "Campana", "Concordia", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		
		comuna10 = new Comuna(10, limitesComuna10);
		
		posicionCgpComuna10 = new Point(-34.6369004, -58.4959096);
		
		cgpComuna10 = new CGP(serviciosCGP, comuna10, posicionCgpComuna10, "CGP Comuna 10", direccionCgpComuna10, etiquetasCGP);
	
	
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
	}

	@Test
	public void Parada132NoEstaCercaDeCredicoop() {
		Assert.assertFalse(parada132.estasCerca(bancoCredicoop.getPosicion()));
	}

	@Test
	public void BancoCredicoopEstaCercaDeParada132() {
		Assert.assertTrue(bancoCredicoop.estasCerca(parada132.getPosicion()));
	}

	@Test
	public void Parada132EstaAMasDe3CuadrasDeCredicoop() {
		Assert.assertTrue(parada132.distanciaAPoi(bancoCredicoop) > 0.3);
	}
	
	@Test
	public void MacowinsConsideraComoCerca9Cuadras(){
		
		Assert.assertEquals(0.9,macowins.condicionDeCercania(),0.0);
	}
	
	@Test
	public void MacowinsEstaCercaDeCredicoop(){
		Assert.assertTrue(macowins.estasCerca(bancoCredicoop.getPosicion()));
	}
	
	@Test
	public void CredicoopNoEstaCercaDeMacowins(){
		Assert.assertFalse(bancoCredicoop.estasCerca(macowins.getPosicion()));}
		
	@Test
	public void Comuna10IncluyeABancoProvincia(){
		Assert.assertTrue(comuna10.incluyeA(bancoProvincia.getPosicion()));		
	}
	
	@Test
	public void Comuna10NoIncluyeAMacowins(){
		Assert.assertFalse(comuna10.incluyeA(macowins.getPosicion()));		
	}
	
	@Test
	public void CgpComuna10NoEstaCercaDeMacowins(){
		Assert.assertFalse(cgpComuna10.estasCerca(macowins.getPosicion()));		
	}
	
	@Test
	public void CgpComuna10EstaCercaDeBancoProvincia(){
		Assert.assertTrue(cgpComuna10.estasCerca(bancoProvincia.getPosicion()));		
	}
	
}
