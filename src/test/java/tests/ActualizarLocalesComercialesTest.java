package tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.*;
import pois.Comercio;
import pois.ManejadorDePois;
import pois.POI;
import procesos.AccionActualizarLocalesComerciales;
import procesos.ExceptionErrorEjecucionDeAccion;

public class ActualizarLocalesComercialesTest {

	private String ruta;
	private Comercio comercioValido;
	private Comercio otroComercioValido;

	@Before
	public void init() {
		ManejadorDePois.getInstance().setListaPoisInternos(new ArrayList<POI>());
		ArrayList<String> listaEtiquetas = new ArrayList<String>();
		listaEtiquetas.add("EtiquetaValida");
		listaEtiquetas.add("EtiquetaValida2");
		ArrayList<String> otraListaEtiquetas = new ArrayList<String>();
		otraListaEtiquetas.add("OtraEtiquetaValida");
		otraListaEtiquetas.add("OtraEtiquetaValida2");
		comercioValido = new Comercio(null, null, null, "LocalValido", null, listaEtiquetas);
		otroComercioValido = new Comercio(null, null, null, "OtroLocalValido", null, otraListaEtiquetas);

		ruta = "./PruebaEtiquetasLocal";
		new File(ruta).delete();
		BufferedWriter escritor;
		try {
			escritor = new BufferedWriter(new FileWriter(ruta));
			escritor.write("LocalValido;EtiquetaValida");
			escritor.newLine();
			escritor.close();
		} catch (IOException e) {
		}

	}

	@Test
	public void testEjecutoActualizarLocalesComercialesYModificaPoisEnListaDePois() throws ExceptionErrorEjecucionDeAccion {

		ManejadorDePois.getInstance().agregarPoiInterno(comercioValido);
		AccionActualizarLocalesComerciales accion = new AccionActualizarLocalesComerciales(ruta);

		Assert.assertEquals(1, accion.ejecutar().getCantElementosAfectados(), 0);
		Assert.assertEquals(1, ManejadorDePois.getInstance().getListaPoisInternos().get(0).getEtiquetas().size(), 0);
		Assert.assertTrue(ManejadorDePois.getInstance().getListaPoisInternos().get(0).getEtiquetas().get(0).equals("EtiquetaValida"));		

	}

	@Test
	public void testEjecutoActualizarLocalesComercialesYNoEncuentraLocalesConNombreEnElArchivo() throws ExceptionErrorEjecucionDeAccion {
		// Crear el archivo de prueba

		ManejadorDePois.getInstance().agregarPoiInterno(otroComercioValido);
		ManejadorDePois.getInstance().getListaPoisInternos().get(0).setNombre("OtroLocalValido");
		Integer cantidadEtiquetasInicial = ManejadorDePois.getInstance().getListaPoisInternos().get(0).getEtiquetas().size();
		AccionActualizarLocalesComerciales accion = new AccionActualizarLocalesComerciales(ruta);

		Assert.assertEquals(0, accion.ejecutar().getCantElementosAfectados(), 0);
		Assert.assertEquals(cantidadEtiquetasInicial, ManejadorDePois.getInstance().getListaPoisInternos().get(0).getEtiquetas().size(), 0);
		Assert.assertTrue(ManejadorDePois.getInstance().getListaPoisInternos().get(0).getEtiquetas().get(0).equals("OtraEtiquetaValida"));
		

	}

	// Testear si el archivo esta vacio
	@Test
	public void testEjecutoActualizarLocalesComercialesYElArchivoEstaVacio() throws ExceptionErrorEjecucionDeAccion {
		try {
			
			new File(ruta).delete();			
			AccionActualizarLocalesComerciales accion = new AccionActualizarLocalesComerciales(ruta);
			Assert.assertEquals(0, accion.ejecutar().getCantElementosAfectados(), 0);
			
		} catch (Exception e) {

		}
	}

	@After
	public void after() {
		try {
			ManejadorDePois.getInstance().getListaPoisInternos().clear();
			new File(ruta).delete();
		} catch (Exception e) {

		}
	}

}
