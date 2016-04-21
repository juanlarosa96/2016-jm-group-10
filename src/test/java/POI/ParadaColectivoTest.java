package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class ParadaColectivoTest {
		
	private ParadaColectivo parada114Segurola;
	private Direccion direccionParada114Segurola;
	private Point posicionParada114Segurola;	
	private List<String> etiquetasParada114;
	private DateTime martes5abril2am;
	
	private ParadaColectivo parada132;
	private List<String> etiquetas132;
	private Point posicion132;
	private Direccion direccion132;

	private Banco bancoCredicoop;
	private Point posicionCredicoop;
	private Direccion direccionCredicoop;
	private List<String> etiquetasCredicoop;
	
	@Before
	public void init(){
		posicionParada114Segurola = new Point(-34.631997, -58.484737);
		direccionParada114Segurola = new Direccion("Av. Segurola", 230, "Bacacay", "Bogota", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		etiquetasParada114 = new ArrayList<String>() {
			{
				add("Parada");
				add("parada");
				add("114");
				add("colectivo");
			}
		};
		parada114Segurola = new ParadaColectivo(114, posicionParada114Segurola, "114", direccionParada114Segurola,
				etiquetasParada114);
		
		martes5abril2am = new DateTime(2016, 4, 5, 2, 30);
		
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

	}
	
	
	@Test
	public void colectivoEstaDisponible() {
		Assert.assertTrue(parada114Segurola.estaDisponible(martes5abril2am));
	}

	@Test
	public void Parada132NoEstaCercaDeCredicoop() {
		Assert.assertFalse(parada132.estasCerca(bancoCredicoop.getPosicion()));
	}
	
	@Test
	public void Parada132EstaAMasDe3CuadrasDeCredicoop() {
		Assert.assertTrue(parada132.distanciaAPoi(bancoCredicoop) > 0.3);
	}

}
