package POI;

import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import org.uqbar.geodds.Point;

public class ComercioTest {
	
	private Comercio elHalcon;
	private Direccion direccionElHalcon;
	private Point posicionElHalcon;
	private Rubro restaurant;
	private List<FranjaHoraria> horariosElHalcon;
	private List<String> etiquetasElHalcon;
	private DateTime martes19abril10am;
	private DateTime martes19abril1am;
	
	@Before
	public void init(){
		
		martes19abril10am = new DateTime (2016,4,19,10,0,0);
		martes19abril1am = new DateTime (2016,4,19,1,0,0);
		
		posicionElHalcon = new Point(-34.6327106, -58.4877209);
		direccionElHalcon = new Direccion("Av. Rivadavia", 8451, "Mercedes", "Av. Segurola", null, null, 1407, "CABA",
				"Floresta", "CABA", "Argentina");
		horariosElHalcon = new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(1, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(1, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(2, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(2, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(3, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(3, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(4, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(4, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(5, new LocalTime(9, 30), new LocalTime(13, 0)));
				add(new FranjaHoraria(5, new LocalTime(14, 30), new LocalTime(19, 0)));
				add(new FranjaHoraria(6, new LocalTime(10, 0), new LocalTime(14, 0)));

			}
		};
		etiquetasElHalcon = new ArrayList<String>() {
			{
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
		elHalcon = new Comercio(restaurant, horariosElHalcon, posicionElHalcon, "El Halcon", direccionElHalcon,
				etiquetasElHalcon);
				
	}
	
	@Test
	public void estaDisponibleElHalcon (){
		Assert.assertTrue(elHalcon.estaDisponible(martes19abril10am));		
	}
	
	@Test
	public void noEstaDisponibleElHalcon (){
		Assert.assertFalse(elHalcon.estaDisponible(martes19abril1am));		
	}

}
