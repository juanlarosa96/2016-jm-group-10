package tests;

import java.util.ArrayList;
import org.junit.*;
import fixtures.FixtureComercio;
import pois.Comercio;
import pois.ManejadorDePois;
import pois.POI;
import procesos.AccionActualizarLocalesComerciales;
import procesos.ExceptionErrorEjecucionDeAccion;

public class ActualizarLocalesComercialesTest {
	
	private String ruta ;

	@Before
	public void init() {
		ManejadorDePois.getInstance().setListaPois(new ArrayList<POI>());
		ruta = "./PruebaEtiquetasLocal";
	}

	@Test
	public void testEjecutoActualizarLocalesComercialesYModificaPoisEnListaDePois() throws ExceptionErrorEjecucionDeAccion {
		
		ArrayList<String> listaEtiquetas = new ArrayList<String>();
		listaEtiquetas.add("EtiquetaValida");
		listaEtiquetas.add("EtiquetaValida2");
		Comercio comercio = new Comercio(null,null,null,"LocalValido",null, listaEtiquetas);
		ManejadorDePois.getInstance().agregarPoi(comercio);
		AccionActualizarLocalesComerciales accion = new AccionActualizarLocalesComerciales(ruta);

		Assert.assertEquals(1, accion.ejecutar().getCantElementosAfectados(), 0);
		Assert.assertEquals(1, ManejadorDePois.getInstance().listaPois.get(0).getEtiquetas().size(), 0);
		Assert.assertTrue(ManejadorDePois.getInstance().listaPois.get(0).getEtiquetas().get(0).equals("EtiquetaValida"));
		ManejadorDePois.getInstance().listaPois.clear();

	}
	
	@Test
	public void testEjecutoActualizarLocalesComercialesYNoEncuentraLocalesConNombreEnElArchivo() throws ExceptionErrorEjecucionDeAccion {
		//Crear el archivo de prueba
		
		ArrayList<String> listaEtiquetas = new ArrayList<String>();
		listaEtiquetas.add("OtraEtiquetaValida");
		listaEtiquetas.add("OtraEtiquetaValida2");
		Comercio comercio = new Comercio(null,null,null,"OtroLocalValido",null, listaEtiquetas);
		ManejadorDePois.getInstance().agregarPoi(comercio);		
		
		ManejadorDePois.getInstance().listaPois.get(0).setNombre("OtroLocalValido");
		Integer cantidadEtiquetasInicial = ManejadorDePois.getInstance().listaPois.get(0).getEtiquetas().size();
		AccionActualizarLocalesComerciales accion = new AccionActualizarLocalesComerciales(ruta);

		Assert.assertEquals(0, accion.ejecutar().getCantElementosAfectados(), 0);
		Assert.assertEquals(cantidadEtiquetasInicial, ManejadorDePois.getInstance().listaPois.get(0).getEtiquetas().size(), 0);
		Assert.assertTrue(ManejadorDePois.getInstance().listaPois.get(0).getEtiquetas().get(0).equals("OtraEtiquetaValida"));
		ManejadorDePois.getInstance().listaPois.clear(); //After

	}
	
	//Testear si el archivo esta vacio

}
