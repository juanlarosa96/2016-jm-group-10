package tests;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adapters.BancoAdapter;
import adapters.CentroDTO;
import adapters.CgpAdapter;
import adapters.ComponenteExternoAdapter;
import adapters.ServicioExternoBancos;
import adapters.ServicioExternoCGP;
import static org.mockito.Mockito.*;
import fixtures.FixtureBanco;
import fixtures.FixtureBancoAdapter;
import fixtures.FixtureCGP;
import fixtures.FixtureCentroDTO;
import fixtures.FixtureComercio;
import fixtures.FixtureParadaColectivo;
import herramientas.EntityManagerHelper;
import pois.Banco;
import pois.CGP;
import pois.Comercio;
import pois.ManejadorDePois;
import pois.POI;
import pois.ParadaColectivo;

public class ManejadorDePoisTest {

	private CGP cgpValido;
	private CGP otroCgpValido;

	private Banco bancoValido;

	private Comercio comercioValido;

	private ParadaColectivo parada114Valida;
	private ParadaColectivo otraParada114Valida;

	private List<POI> listaPoisDispositivo;
	private DateTime horarioValidoParaRentas;
	private DateTime horarioNoValidoParaNingunServicio;
	private List<POI> CGPsConRentas;

	private ServicioExternoCGP servicioExternoCgpMockeado;
	private ArrayList<CentroDTO> centrosDTO;
	private CentroDTO centroDTO1;

	private String listaBancoJson;
	private String listaVaciaBancoJson;
	private ServicioExternoBancos servicioExternoBancoMockeado;

	private ParadaColectivo paradaQueNoEstaEnLaLista;
	private ParadaColectivo parada114ValidaConMasEtiquetas;

	private CgpAdapter cgpAdapter;
	private BancoAdapter bancoAdapter;
	private List<ComponenteExternoAdapter> listaAdapters;

	private int tamanioListaPois;
	private ManejadorDePois manejadorDePois;

	@Before
	public void init() {
		horarioValidoParaRentas = new DateTime(2016, 4, 4, 10, 0);
		horarioNoValidoParaNingunServicio = new DateTime(2016, 4, 5, 2, 30);

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

		tamanioListaPois = listaPoisDispositivo.size();

		CGPsConRentas = new ArrayList<POI>();

		servicioExternoCgpMockeado = mock(ServicioExternoCGP.class);
		servicioExternoBancoMockeado = mock(ServicioExternoBancos.class);

		paradaQueNoEstaEnLaLista = FixtureParadaColectivo.dameUnaTercerParadaValida();
		parada114ValidaConMasEtiquetas = FixtureParadaColectivo.dameUnaParadaValidaConMasEtiquetas();

		// CGP Adapter
		cgpAdapter = new CgpAdapter(servicioExternoCgpMockeado);

		// Banco Adapter

		bancoAdapter = new BancoAdapter(servicioExternoBancoMockeado);

		listaAdapters = new ArrayList<ComponenteExternoAdapter>();

		manejadorDePois.setListaAdapters(listaAdapters);

		// Servicio Externo
		centroDTO1 = FixtureCentroDTO.dameCentroDTO1();
		centrosDTO = new ArrayList<CentroDTO>() {
			{
				add(centroDTO1);
			}
		};

		listaBancoJson = FixtureBancoAdapter.devolverListaBancoJsonNoVacia();
		listaVaciaBancoJson = FixtureBancoAdapter.devolverListaBancoJsonVacia();

	}

	@Test
	public void SiPersistoUnPOILuegoLoEncuentro() {

		EntityManagerHelper.beginTransaction();

		EntityManagerHelper.persist(cgpValido);

		EntityManagerHelper.commit();

		Assert.assertTrue(EntityManagerHelper.contains(cgpValido));

		CGP cgpEncontrado = EntityManagerHelper.find(CGP.class, cgpValido.getId());

		Assert.assertTrue(cgpValido.getNombre().equals(cgpEncontrado.getNombre()));

		System.out.println("nombre: " + cgpEncontrado.getNombre() + "; id: " + cgpEncontrado.getId().toString());

	}

	@Test
	public void SiBuscoParadaQueEstaEnLaListaDePoisPorEtiquetaLaEncuentra() {
		manejadorDePois.agregarPoi(parada114Valida);
		Assert.assertTrue((manejadorDePois.buscarPOIs("114")).contains(parada114Valida));
	}

	@Test
	public void SiBuscoParadasPorEtiquetaEncuentraTodasLasQueEstanEnLaListaConEsaEtiqueta() {
		List<POI> poisEncontrados = manejadorDePois.buscarPOIs("114");

		Assert.assertTrue(poisEncontrados.contains(parada114Valida));
		Assert.assertTrue(poisEncontrados.contains(otraParada114Valida));
	}

	@Test
	public void SiBuscoPOIsPorPalabraClaveDevuelveTodosLosQueLaTienen() {
		Assert.assertEquals(2, manejadorDePois.buscarPOIs("tarjeta de credito").size(), 0);
	}

	@Test
	public void SiBuscoCGPsPorPalabraClaveYPreguntoCuantosSonDevuelveLaCantidadDeCGPsQueLaTienen() {
		Assert.assertEquals(2, (manejadorDePois.buscarPOIs("asesoramiento").size()), 0);
	}

	@Test
	public void SiBuscoPOIsPorEtiquetaQueNingunoTieneNoEncuentraNinguno() {
		Assert.assertTrue(manejadorDePois.buscarPOIs("negra").isEmpty());
	}

	@Test
	public void SiBuscoUnServicioQueSeEncuentraDisponibleEn2CGPEnUnHorarioDisponibleParaEseServicioEncuentraLos2CGP() {
		CGPsConRentas = manejadorDePois.buscarServicioDisponible("Rentas", horarioValidoParaRentas);
		Assert.assertEquals(2, CGPsConRentas.size(), 0);
		Assert.assertTrue(CGPsConRentas.contains(cgpValido));
		Assert.assertTrue(CGPsConRentas.contains(otroCgpValido));
	}

	@Test
	public void SiBuscoSiUnServicioEstaDisponibleEnUnHorarioEnQueEstaCerradoNoEncuentraNinguno() {
		CGPsConRentas = manejadorDePois.buscarServicioDisponible("Rentas", horarioNoValidoParaNingunServicio);
		Assert.assertEquals(0, CGPsConRentas.size(), 0);
	}

	// Servicios
	// Externos------------------------------------------------------------------

	@Test
	public void SiBuscoEnElServicioExternoConZonaValidaSeAgreganLosCGPsCorrespondientesEnLaListaDePOIs() {
		listaAdapters.clear();
		listaAdapters.add(cgpAdapter);
		manejadorDePois.setListaAdapters(listaAdapters);
		when(servicioExternoCgpMockeado.buscar("balvanera")).thenReturn(centrosDTO);

		manejadorDePois.buscarPOIs("balvanera");

		verify(servicioExternoCgpMockeado).buscar("balvanera");

		Assert.assertEquals(tamanioListaPois + 1, listaPoisDispositivo.size(), 0);

	}

	@Test
	public void SiBuscoEnElServicioExternoConUnaZonaInvalidaNoSeAgregaNingunCGPALaListaDePOIs() {
		listaAdapters.clear();
		listaAdapters.add(cgpAdapter);
		manejadorDePois.setListaAdapters(listaAdapters);
		centrosDTO.clear();
		when(servicioExternoCgpMockeado.buscar("manchester")).thenReturn(centrosDTO);

		manejadorDePois.buscarPOIs("manchester");

		verify(servicioExternoCgpMockeado).buscar("manchester");

		Assert.assertEquals(6, listaPoisDispositivo.size(), 0);
	}

	@Test
	public void SiBuscoEnElServicioExternoConServicioDeBancoDisponibleSeAgreganLosBancosCorrespondientesEnLaListaDePOIs() {
		listaAdapters.clear();
		listaAdapters.add(bancoAdapter);
		manejadorDePois.setListaAdapters(listaAdapters);
		when(servicioExternoBancoMockeado.buscar("Banco de la Plaza", "extracciones")).thenReturn(listaBancoJson);
		manejadorDePois.buscarPOIs("Banco de la Plaza,extracciones");

		verify(servicioExternoBancoMockeado).buscar("Banco de la Plaza", "extracciones");

		Assert.assertEquals(tamanioListaPois + 1, listaPoisDispositivo.size(), 0);

	}

	@Test
	public void SiBuscoEnElServicioExternoConServicioDeBancoNoDisponibleNoSeAgregaNingunBancoEnLaListaDePOIs() {
		listaAdapters.clear();
		listaAdapters.add(bancoAdapter);
		manejadorDePois.setListaAdapters(listaAdapters);
		when(servicioExternoBancoMockeado.buscar("", "")).thenReturn(listaVaciaBancoJson);
		manejadorDePois.buscarPOIs(",");

		verify(servicioExternoBancoMockeado).buscar("", "");
		Assert.assertEquals(6, listaPoisDispositivo.size(), 0);
	}

	// ------------------------------------------------------------------------
	@Test
	public void SiEliminoUnaParadaDeLaListaDePoisEntoncesLaElimina() {
		manejadorDePois.eliminarPOI(parada114Valida);
		Assert.assertFalse(listaPoisDispositivo.contains(parada114Valida));
	}

	@Test
	public void SiAgregoUnaParadaQueNoEstaEnLaListaLaAgrega() {
		Assert.assertFalse(listaPoisDispositivo.contains(paradaQueNoEstaEnLaLista));
		manejadorDePois.agregarPoi(paradaQueNoEstaEnLaLista);
		Assert.assertTrue(listaPoisDispositivo.contains(paradaQueNoEstaEnLaLista));

	}

	@Test
	public void SiAgregoUnaParadaExistenteLaActualiza() {
		manejadorDePois.agregarPoi(parada114ValidaConMasEtiquetas);
		Assert.assertFalse(listaPoisDispositivo.contains(parada114Valida));
		Assert.assertTrue(listaPoisDispositivo.contains(parada114ValidaConMasEtiquetas));
	}

}
