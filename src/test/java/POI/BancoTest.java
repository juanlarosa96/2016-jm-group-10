package POI;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class BancoTest {
		
	private DateTime lunes4abril10am;
	private DateTime martes5abri12am;
	private Banco bancoProvincia;
	private Direccion direccionBancoProvincia;
	private Point posicionBancoProvincia;
	private List<String> etiquetasBancoProvincia;
	
	@Before
	public void init(){
	
	
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
			add("tarjeta");
			add("credito");
			add("debito");
		}
	};
	lunes4abril10am= new DateTime (2016,4,4,10,0,0);
	martes5abri12am=new DateTime(2016,5,4,00,0,0);
	bancoProvincia = new Banco(posicionBancoProvincia, "Banco Provincia", direccionBancoProvincia,
			etiquetasBancoProvincia);
	
	}


	
	@Test
	public void bancoProvinciaEstaDisponibleAlas10am() {
		Assert.assertTrue(bancoProvincia.estaDisponible(lunes4abril10am));
	}

	@Test
	public void bancoProvinciaNoEstaDisponibleAlas12deLaNoche() {
		Assert.assertFalse(bancoProvincia.estaDisponible(martes5abri12am));
	}

}
