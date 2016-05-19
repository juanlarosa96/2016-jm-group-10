package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fixtures.FixtureCentroDTO;
import tpaPOIs.CGP;
import tpaPOIs.CentroDTO;
import tpaPOIs.CgpAdapter;
import tpaPOIs.Direccion;
import tpaPOIs.POI;
import tpaPOIs.ServicioExternoCGP;

public class CgpAdapterTest {

	private ServicioExternoCGP servicioExternoCgpMockeado;
	private CentroDTO centroDTO1;
	private ArrayList<CentroDTO> centrosDTO;
	private String domicilioCentroDTO1;
	private String[] calleyNumero;
	private CgpAdapter cgpAdapter;
	private List<CentroDTO> listaVaciaCentrosDTO;

	@Before
	public void init() {	
		// Servicio Externo
		centroDTO1 = FixtureCentroDTO.dameCentroDTO1();
		centrosDTO = new ArrayList<CentroDTO>() {
			{
				add(centroDTO1);
			}
		};
		
		listaVaciaCentrosDTO= new ArrayList<CentroDTO>();
		
		servicioExternoCgpMockeado = mock(ServicioExternoCGP.class);
		when(servicioExternoCgpMockeado.buscar("manchester")).thenReturn(listaVaciaCentrosDTO);
		when(servicioExternoCgpMockeado.buscar("balvanera")).thenReturn(centrosDTO);
		
		// CGP Adapter
				cgpAdapter = new CgpAdapter(servicioExternoCgpMockeado);
	}

	@Test
	public void SiBuscoEnElServicioExternoConZonaValidaMeDevuelveElCGPCorrespondienteAdaptado() {
		
		List<POI> cgpsEncontrados = cgpAdapter.buscarPoisExternos("balvanera");

		verify(servicioExternoCgpMockeado).buscar("balvanera");

		CGP cgpAdaptado = (CGP) cgpsEncontrados.get(0);
		
		Direccion direccionCgpAdaptado = cgpAdaptado.getDireccion();
		
		domicilioCentroDTO1 = centroDTO1.getDomicilioCentroDTO();

		calleyNumero = domicilioCentroDTO1.split(" ", 2);
		

		Assert.assertTrue(calleyNumero[0].equals(direccionCgpAdaptado.getCalle()));

		Assert.assertTrue(calleyNumero[1].equals(direccionCgpAdaptado.getAltura().toString()));

		Assert.assertTrue(centroDTO1.getNumeroComunaCentroDTO() == cgpAdaptado.dameNumeroComuna());

	}
	
	@Test
	public void SiBuscoEnElServicioExternoConUnaZonaInvalidaMeDevuelveListaVacia() {
		
		List<POI> cgpsEncontrados = cgpAdapter.buscarPoisExternos("manchester");

		verify(servicioExternoCgpMockeado).buscar("manchester");

		Assert.assertTrue(cgpsEncontrados.isEmpty());

	}

}
