package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adapters.BancoAdapter;
import adapters.ServicioExternoBancos;
import fixtures.FixtureBancoAdapter;
import pois.Banco;
import pois.POI;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class BancoAdapterTest {

	private ServicioExternoBancos componenteBancos;
	private BancoAdapter bancoAdapter;
	private Banco unBanco;
	private String jsonListaBancos;
	private String jsonListaVacia;

	@Before
	public void init() {

		jsonListaBancos = FixtureBancoAdapter.devolverListaBancoJsonNoVacia();
		jsonListaVacia = FixtureBancoAdapter.devolverListaBancoJsonVacia();
		unBanco = FixtureBancoAdapter.devolverBancoValido();		
		
		componenteBancos = mock(ServicioExternoBancos.class);
		bancoAdapter = new BancoAdapter(componenteBancos);
		
		when(componenteBancos.buscar("Banco de la Plaza", "extracciones")).thenReturn(jsonListaBancos);
		when(componenteBancos.buscar("","")).thenReturn(jsonListaVacia);
		when(componenteBancos.buscar("Banco","")).thenReturn(jsonListaVacia);
		
	
	}

	@Test
	public void SiBuscoEnElServicioExternoBancosPorNombreYServicioYEncuentraAlgunBancoMeDevuelveUnaListaDeBancos() {
		ArrayList<POI> listaBancos = bancoAdapter.buscarPoisExternos("Banco de la Plaza,extracciones");
		Banco bancoRecibido = (Banco) listaBancos.get(0);		
		
		Assert.assertTrue(unBanco.getNombre().equals(bancoRecibido.getNombre()));
		Assert.assertTrue(unBanco.getDireccion() == bancoRecibido.getDireccion());		
		Assert.assertTrue(unBanco.getEtiquetas().equals(bancoRecibido.getEtiquetas()));
		Assert.assertEquals(unBanco.getPosicion().latitude(),bancoRecibido.getPosicion().latitude(),0);
		Assert.assertEquals(unBanco.getPosicion().longitude(),bancoRecibido.getPosicion().longitude(),0);

	}
	
	@Test
	public void SiBuscoEnElServicioExternoBancosPorNombreYServicioVaciosNoEncuentraNingunBancoYDevuelveListaVacia() {
		ArrayList<POI> listaBancos = bancoAdapter.buscarPoisExternos(",");
		Assert.assertTrue(listaBancos.isEmpty());
	}
	
	@Test
	public void SiBuscoEnElServicioExternoBancosConUnParametroSinComaNoEncuentraNingunBancoYDevuelveListaVacia() {
		ArrayList<POI> listaBancos = bancoAdapter.buscarPoisExternos("Banco");
		Assert.assertTrue(listaBancos.isEmpty());
	}
	
	@Test
	public void SiBuscoEnElServicioExternoBancosConUnParametroVacioNoEncuentraNingunBancoYDevuelveListaVacia() {
		ArrayList<POI> listaBancos = bancoAdapter.buscarPoisExternos("");
		Assert.assertTrue(listaBancos.isEmpty());
	}
	
	
}
