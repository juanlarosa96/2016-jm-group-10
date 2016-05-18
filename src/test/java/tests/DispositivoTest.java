package tests;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import static org.mockito.Mockito.*;

import fixtures.FixtureBanco;
import fixtures.FixtureCGP;
import fixtures.FixtureCentroDTO;
import fixtures.FixtureComercio;
import fixtures.FixtureParadaColectivo;
import tpaPOIs.Banco;
import tpaPOIs.CGP;
import tpaPOIs.CentroDTO;
import tpaPOIs.CgpAdapter;
import tpaPOIs.Comercio;
import tpaPOIs.ConsultorExterno;
import tpaPOIs.Dispositivo;
import tpaPOIs.POI;
import tpaPOIs.ParadaColectivo;
import tpaPOIs.ServicioExternoCGP;

public class DispositivoTest {

	private Dispositivo dispositivo;
	private Point posicionDispositivo;

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
<<<<<<< HEAD
	
	private ServicioExternoCGP servicioExternoCgp;
	private ArrayList<CentroDTO> centrosDTO;
	private CentroDTO centroDTO1;
=======
	private ParadaColectivo paradaQueNoEstaEnLaLista;
	private ParadaColectivo parada114ValidaConMasEtiquetas;
>>>>>>> f564bcde64b8d5fad44fdcdf652e951187316ada

	@Before
	public void init() {
		posicionDispositivo = new Point(-34.631402, -58.488060);
		dispositivo = new Dispositivo(posicionDispositivo);

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

		Dispositivo.setListaPois(listaPoisDispositivo);

		CGPsConRentas = new ArrayList<POI>();
		
<<<<<<< HEAD
		//Servicio Externo
		centroDTO1 = FixtureCentroDTO.dameCentroDTO1();		
		centrosDTO = new ArrayList<CentroDTO>(){
			{
				add(centroDTO1);
			}
		};
		
		
=======
		paradaQueNoEstaEnLaLista= FixtureParadaColectivo.dameUnaTercerParadaValida();
		 parada114ValidaConMasEtiquetas= FixtureParadaColectivo.dameUnaParadaValidaConMasEtiquetas();
>>>>>>> f564bcde64b8d5fad44fdcdf652e951187316ada
	}

	@Test
	public void SiBuscoParadaQueEstaEnLaListaDePoisPorEtiquetaLaEncuentra() {
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(parada114Valida));
	}
	
	@Test
	public void SiBuscoParadasPorEtiquetaEncuentraTodasLasQueEstanEnLaListaConEsaEtiqueta() {
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(parada114Valida));
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(otraParada114Valida));
	}

	@Test
	public void SiBuscoPOIsPorPalabraClaveDevuelveTodosLosQueLaTienen() {
		Assert.assertEquals(2, dispositivo.buscarPOIs("tarjeta de credito").size(), 0);
	}

	@Test
	public void SiBuscoCGPsPorPalabraClaveYPreguntoCuantosSonDevuelveLaCantidadDeCGPsQueLaTienen() {
		Assert.assertEquals(2, (dispositivo.buscarPOIs("asesoramiento").size()), 0);
	}
	
	@Test
	public void SiBuscoPOIsPorEtiquetaQueNingunoTieneNoEncuentraNinguno() {
		Assert.assertTrue(dispositivo.buscarPOIs("negra").isEmpty());
	}	

	@Test
	public void SiBuscoUnServicioQueSeEncuentraDisponibleEn2CGPEnUnHorarioDisponibleParaEseServicioEncuentraLos2CGP() {
		CGPsConRentas = dispositivo.buscarServicioDisponible("Rentas", horarioValidoParaRentas);
		Assert.assertEquals(2, CGPsConRentas.size(), 0);
		Assert.assertTrue(CGPsConRentas.contains(cgpValido));
		Assert.assertTrue(CGPsConRentas.contains(otroCgpValido));
	}

	@Test
	public void SiBuscoSiUnServicioEstaDisponibleEnUnHorarioEnQueEstaCerradoNoEncuentraNinguno() {
		CGPsConRentas = dispositivo.buscarServicioDisponible("Rentas", horarioNoValidoParaNingunServicio);
		Assert.assertEquals(0, CGPsConRentas.size(), 0);
	}

	/*@Test
	public void SiLePasoAUnCGPAdapterUnCentroDTOQueNoEstaEnLaListaLoAgrega(){
		servicioExternoCgp = mock(ServicioExternoCGP.class);
		CgpAdapter cgpAdapter = new CgpAdapter(servicioExternoCgp);
		ArrayList<POI> cgpsExternos = cgpAdapter.adaptarCentrosDTO(centrosDTO);
		dispositivo.agregarPois(cgpsExternos);
		Assert.assertEquals(7, listaPoisDispositivo.size(),0);
	}*/
	
	@Test
	public void SeAgreganLosCGPsCorrespondientesEnLaListaDePOIsCuandoBuscoEnElServicioExterno(){
		servicioExternoCgp = mock(ServicioExternoCGP.class);
		when(servicioExternoCgp.buscar("rentas")).thenReturn(centrosDTO);
		CgpAdapter cgpAdapter = new CgpAdapter(servicioExternoCgp);
		ConsultorExterno.agregarAdapter(cgpAdapter);
		dispositivo.buscarPOIs("rentas");
		
		verify(servicioExternoCgp).buscar("rentas");
		Assert.assertEquals(7, listaPoisDispositivo.size(),0);
	}

	@Test
	public void SiEliminoUnaParadaDeLaListaDePoisEntoncesLaElimina() {
	Dispositivo.eliminarPOI(parada114Valida);
	Assert.assertFalse(listaPoisDispositivo.contains(parada114Valida));	
	}

	@Test
	public void SiAgregoUnaParadaQueNoEstaEnLaListaLaAgrega(){
		Assert.assertFalse(listaPoisDispositivo.contains(paradaQueNoEstaEnLaLista));
		Dispositivo.agregarPoi(paradaQueNoEstaEnLaLista);
		Assert.assertTrue(listaPoisDispositivo.contains(paradaQueNoEstaEnLaLista));
		
	}
	@Test
	public void SiAgregoUnaParadaExistenteLaActualiza(){
		Dispositivo.agregarPoi(parada114ValidaConMasEtiquetas);
		Assert.assertFalse(listaPoisDispositivo.contains(parada114Valida));
		Assert.assertTrue(listaPoisDispositivo.contains(parada114ValidaConMasEtiquetas));
	}

}
