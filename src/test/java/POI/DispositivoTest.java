package POI;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class DispositivoTest {

	private Dispositivo dispositivo;
	private Point posicionDispositivo;
	
	private CGP cgpValido;
	private CGP otroCgpValido;

	private Banco bancoValido;
	
	private Comercio comercioValido;

	private ParadaColectivo paradaValida;
	private ParadaColectivo otraParadaValida;
	
	private List<POI> listaPoisDispositivo;
	private DateTime horarioValido;
	private DateTime horarioNoValidoParaNingunServicio;
	private List<POI> CGPsConRentas;


	@Before
	public void init() {
		posicionDispositivo = new Point(-34.631402, -58.488060);
		dispositivo = new Dispositivo(posicionDispositivo);

		horarioValido = new DateTime(2016, 4, 4, 10, 0);
		horarioNoValidoParaNingunServicio = new DateTime(2016, 4, 5, 2, 30);

		paradaValida = FixtureParadaColectivo.dameUnaParadaValida();
		otraParadaValida = FixtureParadaColectivo.dameOtraParadaValida();

		bancoValido = FixtureBanco.dameUnBancoValido();

		cgpValido = FixtureCGP.dameCGPValido();
		otroCgpValido = FixtureCGP.dameOtroCgpValido();
		
		comercioValido = FixtureComercio.dameComercioValido();

		listaPoisDispositivo = new ArrayList<POI>() {
			{
				add(paradaValida);
				add(otraParadaValida);
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
	public void consultoSiUnaParadaValidaSeEncuentraEnLaListaDePoisYDiceQueSi() {
		Assert.assertTrue((dispositivo.buscarPOIs("114")).contains(paradaValida));
	}

	@Test
	public void consultoSiAlgunPOITieneEtiquetaConUnaPalabraClaveYDevuelve2() {
		Assert.assertEquals(2, dispositivo.buscarPOIs("tarjeta de credito").size(), 0);
	}

	@Test
	public void consultoSiAlgunPoiTieneEtiquetaConUnaPalabraClaveQueNingunoTieneYNoHayNinguno() {
		Assert.assertEquals(0, (dispositivo.buscarPOIs("negra").size()), 0);
	}

	@Test
	public void consultoSiAlgunCGPTieneEtiquetaConUnaPalabraClaveYDevuelve2() {
		Assert.assertEquals(2, (dispositivo.buscarPOIs("asesoramiento").size()), 0);
	}

	@Test
	public void consultoSiUnServicioValidoSeEncuentraDisponibleEnHorarioAbiertoEnAlgunCGPYDiceQueSeEncuentraEn2CGP() {
		CGPsConRentas = dispositivo.buscarServicioDisponible("Rentas", horarioValido);
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
