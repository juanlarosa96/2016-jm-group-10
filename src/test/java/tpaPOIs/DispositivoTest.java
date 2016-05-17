package tpaPOIs;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import tpaPOIs.Banco;
import tpaPOIs.CGP;
import tpaPOIs.Comercio;
import tpaPOIs.Dispositivo;
import tpaPOIs.ParadaColectivo;

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
	}

	@Test
	public void buscoParadaQueEstaEnLaListaDePoisPorEtiquetayLaEncuentra() {
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(parada114Valida));
	}
	//Estos 3 son muy parecidos, solo varia en la clase de POI que encuentran.
	@Test
	public void buscoParadasPorEtiquetayEncuentraTodasLasQueEstanEnLaListaConEsaEtiqueta() {
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(parada114Valida));
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(otraParada114Valida));
	}

	@Test
	public void buscoPOIsPorPalabraClaveYDevuelveTodosLosQueLaTienen() {
		Assert.assertEquals(2, dispositivo.buscarPOIs("tarjeta de credito").size(), 0);
	}

	@Test
	public void consultoSiAlgunCGPTieneEtiquetaConUnaPalabraClaveYDevuelve2() {
		Assert.assertEquals(2, (dispositivo.buscarPOIs("asesoramiento").size()), 0);
	}
	//----------------------------------------------------------------------------
	
	@Test
	public void buscoPOIPorEtiquetaQueNingunoTieneYNoEncuentraNinguno() {
		Assert.assertTrue(dispositivo.buscarPOIs("negra").isEmpty());
	}	

	@Test
	public void buscoServicioValidoQueSeEncuentraDisponibleEn2CGPEnHorarioDisponibleParaEseServicioYEncuentraLos2CGP() {
		CGPsConRentas = dispositivo.buscarServicioDisponible("Rentas", horarioValidoParaRentas);
		Assert.assertEquals(2, CGPsConRentas.size(), 0);
		Assert.assertTrue(CGPsConRentas.contains(cgpValido));
		Assert.assertTrue(CGPsConRentas.contains(otroCgpValido));
	}

	@Test
	public void ServicioValidoNoEstaDisponibleEnHorarioCerrado() {
		CGPsConRentas = dispositivo.buscarServicioDisponible("Rentas", horarioNoValidoParaNingunServicio);
		Assert.assertEquals(0, CGPsConRentas.size(), 0);

	}

}
