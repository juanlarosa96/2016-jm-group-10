package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fixtures.FixtureCGP;
import fixtures.FixtureCentroDTO;
import tpaPOIs.CGP;
import tpaPOIs.CentroDTO;
import tpaPOIs.CgpAdapter;
import tpaPOIs.Direccion;
import tpaPOIs.ManejadorDeFechas;
import tpaPOIs.POI;
import tpaPOIs.ServicioExternoCGP;

public class CgpAdapterTest {

	private ServicioExternoCGP servicioExternoCgpMockeado;
	private CentroDTO centroDTO;
	private ArrayList<CentroDTO> centrosDTO;
	private CgpAdapter cgpAdapter;
	private List<CentroDTO> listaVaciaCentrosDTO;
	private CGP cgpValido;

	@Before
	public void init() {	
		cgpValido=FixtureCGP.dameCGPValido();
		// Servicio Externo
		centroDTO = FixtureCentroDTO.dameCentroDTOparecidoACgpValido();
		centrosDTO = new ArrayList<CentroDTO>() {
			{
				add(centroDTO);
			}
		};
		
		listaVaciaCentrosDTO= new ArrayList<CentroDTO>();
		
		servicioExternoCgpMockeado = mock(ServicioExternoCGP.class);
		when(servicioExternoCgpMockeado.buscar("manchester")).thenReturn(listaVaciaCentrosDTO);
		when(servicioExternoCgpMockeado.buscar("floresta")).thenReturn(centrosDTO);
		
		// CGP Adapter
		cgpAdapter = new CgpAdapter(servicioExternoCgpMockeado);
	}

		
	@Test
	public void SiBuscoEnElServicioExternoConZonaValidaMeDevuelveElCGPCorrespondienteAdaptado() {
		
		List<POI> cgpsEncontrados = cgpAdapter.buscarPoisExternos("floresta");

		verify(servicioExternoCgpMockeado).buscar("floresta");

		CGP cgpAdaptado = (CGP) cgpsEncontrados.get(0);
		
		//Nombre		
		Assert.assertTrue(cgpValido.getNombre().equals(cgpAdaptado.getNombre()));
		
		//NombreServicios
		Assert.assertTrue(cgpValido.getNombreServicios().stream().allMatch(servicio -> cgpAdaptado.getNombreServicios().contains(servicio)));		
		Assert.assertTrue(cgpAdaptado.getNombreServicios().stream().allMatch(servicio -> cgpValido.getNombreServicios().contains(servicio)));
		
		//NumeroComuna
		Assert.assertTrue(cgpValido.dameNumeroComuna()==cgpAdaptado.dameNumeroComuna());
		
		//Horarios
		Assert.assertTrue(cgpValido.getHorarios().stream()
				.allMatch(franjaHoraria -> cgpAdaptado.getHorarios().stream()
						.anyMatch(otraFranjaHoraria -> ManejadorDeFechas.sonIguales(franjaHoraria,otraFranjaHoraria))));		
		Assert.assertTrue(cgpAdaptado.getHorarios().stream()
				.allMatch(franjaHoraria -> cgpValido.getHorarios().stream()
						.anyMatch(otraFranjaHoraria -> ManejadorDeFechas.sonIguales(franjaHoraria,otraFranjaHoraria))));	
		//Etiquetas
		Assert.assertTrue(cgpValido.getEtiquetas().stream().allMatch(etiqueta -> cgpAdaptado.getEtiquetas().contains(etiqueta)));		
		Assert.assertTrue(cgpAdaptado.getEtiquetas().stream().allMatch(etiqueta -> cgpValido.getEtiquetas().contains(etiqueta)));
		
		//Direccion
		Direccion direccionCgpValido = cgpValido.getDireccion();
		Direccion direccionCgpAdaptado = cgpAdaptado.getDireccion();
		
		Assert.assertTrue(direccionCgpValido.getCalle().equals(direccionCgpAdaptado.getCalle()));
		Assert.assertTrue(direccionCgpValido.getAltura().equals(direccionCgpAdaptado.getAltura()));
		
	}
	
	@Test
	public void SiBuscoEnElServicioExternoConUnaZonaInvalidaMeDevuelveListaVacia() {
		
		List<POI> cgpsEncontrados = cgpAdapter.buscarPoisExternos("manchester");

		verify(servicioExternoCgpMockeado).buscar("manchester");

		Assert.assertTrue(cgpsEncontrados.isEmpty());

	}

}
