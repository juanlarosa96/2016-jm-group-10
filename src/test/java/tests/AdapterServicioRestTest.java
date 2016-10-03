package tests;

import java.util.ArrayList;
import java.util.List;

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
	private ServicioRest servicioRest;
	private String urlPoisValidos;
	List<POI> ListaPOIsManejador;
	
	@Before
	public void init() {
	manejadorDePois = ManejadorDePois.getInstance();
	
	parada114Segurola = FixtureParadaColectivo.dameUnaParada114Valida();
	

			
	ListaPOIsManejador = new ArrayList<POI>() {
				{
					add(parada114Segurola);
					
				}
			};
	
	manejadorDePois.setListaPoisInternos(ListaPOIsManejador);
	
	listaPOISJson = FixtureAdapterServicioRest.devolverListaPOIJsonNoVacia();
	
	servicioRest = mock(ServicioRest.class);
	
	urlPoisValidos = FixtureAdapterServicioRest.dameURLPoisValidos();
	
	when(servicioRest.buscarPOISDadosDeBaja(anyString())).thenReturn(listaPOISJson);
	
	
	adapterServicioRest = AdapterServicioRest.getInstance();
	adapterServicioRest.setServicioRest(servicioRest);
	
	}
	
	@Test
	public void siAdapterRecibeUnaUrlConPoisValidosEnJsonDevuelveLaListaDePoisValidosCorrespondientes(){
		listaPois = adapterServicioRest.parsearPOISJson(urlPoisValidos);
		Assert.assertEquals(listaPois.size(), 1);
	}
	
	

}
