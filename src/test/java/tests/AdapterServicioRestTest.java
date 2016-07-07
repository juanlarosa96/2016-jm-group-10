package tests;

import java.util.ArrayList;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import pois.ManejadorDePois;
import pois.POI;
import pois.ParadaColectivo;
import procesos.AdapterServicioRest;
import procesos.POIJson;
import fixtures.FixtureAdapterServicioRest;
import fixtures.FixtureParadaColectivo;

public class AdapterServicioRestTest {
	private ManejadorDePois manejadorDePois;
	private AdapterServicioRest adapterServicioRest;
	private ParadaColectivo parada114Segurola; 
	private String listaPOISJson;
	private ArrayList<POI> listaPois;
	private POI unPOI;
	
	@Before
	public void init() {
	manejadorDePois = ManejadorDePois.getInstance();
	
	parada114Segurola = FixtureParadaColectivo.dameUnaParada114Valida();
	
	listaPOISJson = FixtureAdapterServicioRest.devolverListaPOIJsonNoVacia();
	adapterServicioRest = AdapterServicioRest.getInstance();
	
	}
	
	@Test
	public void siAdapterRecibeUnJsonConPoisDevuelveListaDePoisValidos(){
		listaPois = adapterServicioRest.buscarPoisDadosDeBaja(listaPOISJson);
		Assert.assertEquals(listaPois.size(), 1);
	}
	
	

}
