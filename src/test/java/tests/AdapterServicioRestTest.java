package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import fixtures.FixtureAdapterServicioRest;
import fixtures.FixtureParadaColectivo;
import pois.ManejadorDePois;
import pois.POI;
import pois.ParadaColectivo;
import procesos.AdapterServicioRest;
import procesos.ServicioRest;


public class AdapterServicioRestTest {
	private ManejadorDePois manejadorDePois;
	private AdapterServicioRest adapterServicioRest;
	private ParadaColectivo parada114Segurola; 
	private String listaPOISJson;
	private ArrayList<POI> listaPois;
	private POI unPOI;
	private ServicioRest servicioRest;
	private String stringPoisValidos;
	
	@Before
	public void init() {
	manejadorDePois = ManejadorDePois.getInstance();
	
	parada114Segurola = FixtureParadaColectivo.dameUnaParada114Valida();
	
	listaPOISJson = FixtureAdapterServicioRest.devolverListaPOIJsonNoVacia();
	
	servicioRest = mock(ServicioRest.class);
	
	stringPoisValidos = FixtureAdapterServicioRest.dameStringPoisValidos();
	
	when(servicioRest.obtenerContenidoEnStringDeURL(anyString())).thenReturn(stringPoisValidos);
	
	
	adapterServicioRest = AdapterServicioRest.getInstance();
	adapterServicioRest.setServicioRest(servicioRest);
	
	}
	
	@Test
	public void siAdapterRecibeUnaUrlConPoisValidosEnJsonDevuelveLaListaDePoisValidosCorrespondientes(){
		listaPois = adapterServicioRest.buscarPoisDadosDeBaja(listaPOISJson);
		Assert.assertEquals(listaPois.size(), 1);
	}
	
	

}
