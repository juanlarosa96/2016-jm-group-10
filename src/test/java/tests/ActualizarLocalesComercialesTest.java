package tests;

import java.util.ArrayList;

import org.junit.*;

import fixtures.FixtureComercio;
import pois.ManejadorDePois;
import pois.POI;
import procesos.AccionActualizarLocalesComerciales;
import procesos.ExceptionErrorEjecucionDeAccion;

public class ActualizarLocalesComercialesTest {

	@Before
	public void init() {
		ManejadorDePois.getInstance().setListaPois(new ArrayList<POI>());
		ManejadorDePois.getInstance().agregarPoi(FixtureComercio.dameComercioValido());
	}

	@Test
	public void testEjecutoActualizarLocalesComercialesYModificaPoisEnListaDePois() throws ExceptionErrorEjecucionDeAccion {
		String ruta = "./PruebaEtiquetasLocal";
		AccionActualizarLocalesComerciales accion = new AccionActualizarLocalesComerciales(ruta);

		Assert.assertEquals(1, accion.ejecutar().getCantElementosAfectados(), 0);
		Assert.assertEquals(1, ManejadorDePois.getInstance().listaPois.get(0).getEtiquetas().size(), 0);
		Assert.assertTrue(ManejadorDePois.getInstance().listaPois.get(0).getEtiquetas().get(0).equals("EtiquetaValida"));

	}

}
