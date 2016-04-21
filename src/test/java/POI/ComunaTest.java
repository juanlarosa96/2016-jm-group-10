package POI;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class ComunaTest {
	
	private Comercio macowins;
	private Rubro localDeRopa;
	private Point posicionMacowins;
	private Direccion direccionMacowins;
	private List<String> etiquetasMacowins;
	
	private Comuna comuna10;	
	private List<Point> limitesComuna10;
	
	private Point posicionBancoProvincia;
	private Direccion direccionBancoProvincia;
	private ArrayList<String> etiquetasBancoProvincia;
	private Banco bancoProvincia;
	
	@Before
	public void init(){
		localDeRopa = new Rubro("Local de Ropa", 0.9);
		posicionMacowins = new Point(-34.6184994, -58.4368164);
		direccionMacowins = new Direccion("Av. Acoyte", 56, "Av. Rivadavia", "Yerbal", null, null, 1424, "CABA",
				"Caballito", "Buenos Aires", "Argentina");
		etiquetasMacowins= new ArrayList<String>(){{add("local");add("ropa");add("macowins");}};
		macowins = new Comercio(localDeRopa, null, posicionMacowins, "Macowins", direccionMacowins, etiquetasMacowins);
		
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
	public void Comuna10IncluyeABancoProvincia(){
		Assert.assertTrue(comuna10.incluyeA(bancoProvincia.getPosicion()));		
	}
	
	@Test
	public void Comuna10NoIncluyeAMacowins(){
		Assert.assertFalse(comuna10.incluyeA(macowins.getPosicion()));		
	}
	
}
