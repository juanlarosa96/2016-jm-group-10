package tests;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import adapters.ComponenteExternoAdapter;
import eventosBusqueda.ManejadorDeReportes;
import fixtures.FixtureBanco;
import fixtures.FixtureCGP;
import fixtures.FixtureComercio;
import fixtures.FixtureParadaColectivo;
import herramientas.ManejadorDeFechas;
import pois.Banco;
import pois.CGP;
import pois.Comercio;
import pois.Dispositivo;
import pois.ManejadorDePois;
import pois.POI;
import pois.ParadaColectivo;

public class DispositivoTest {
	
	private CGP cgpValido;
	private CGP otroCgpValido;

	private Banco bancoValido;

	private Comercio comercioValido;

	private ParadaColectivo parada114Valida;
	private ParadaColectivo otraParada114Valida;

	private List<POI> listaPoisDispositivo;
	private ManejadorDePois manejadorDePois;
	
	private Dispositivo dispositivo;
	
	private ManejadorDeReportes manejadorDeReportes;

	@Before
	public void init() {
		
		
		parada114Valida = FixtureParadaColectivo.dameUnaParada114Valida();
		otraParada114Valida = FixtureParadaColectivo.dameOtraParada114Valida();

		bancoValido = FixtureBanco.dameUnBancoValido();

		cgpValido = FixtureCGP.dameCGPValido();
		otroCgpValido = FixtureCGP.dameOtroCgpValido();

		comercioValido = FixtureComercio.dameComercioValido();

		listaPoisDispositivo = new ArrayList<POI>() {
			{
				add(parada114Valida);
				add(otraParada114Valida);
				add(bancoValido);
				add(cgpValido);
				add(otroCgpValido);
				add(comercioValido);
			}
		};

		manejadorDePois = ManejadorDePois.getInstance();
		
		manejadorDePois.setListaPois(listaPoisDispositivo);
		
		dispositivo = new Dispositivo("DispositivoValido", new Point(-34.6327475, -58.4851584));
		manejadorDePois.setListaAdapters(new ArrayList<ComponenteExternoAdapter>());
		
		
		manejadorDeReportes = ManejadorDeReportes.getInstance();	
		manejadorDeReportes.limpiarBusquedas();
		
	}
	
	@Test
	public void BuscoPoiPorDescripcionConReporteHabilitadoYLuegoRealizoOtraBusquedaConReporteDeshabilitadoYSoloTomaEnCuentaLaPrimeraBusqueda(){
		
		dispositivo.agregarInteresadoEnBusquedas(manejadorDeReportes);		
		dispositivo.buscarPOIs("114");
		dispositivo.eliminarInteresadoEnBusquedas(manejadorDeReportes);
		dispositivo.buscarPOIs("114");
		Assert.assertEquals(2, manejadorDeReportes.generarReporteBusquedasPorFecha().get(ManejadorDeFechas.convertirFechaAString(DateTime.now())),0);				
		
	}
	

}
