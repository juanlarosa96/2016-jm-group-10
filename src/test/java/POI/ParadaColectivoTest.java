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
	}
	
	
	@Test
	public void colectivoEstaDisponible() {
		Assert.assertTrue(parada114Segurola.estaDisponible(martes5abril2am));
	}


}
