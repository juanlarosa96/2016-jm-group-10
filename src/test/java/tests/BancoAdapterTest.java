package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static org.mockito.Mockito.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import tpaPOIs.Banco;
import tpaPOIs.BancoAdapter;
import tpaPOIs.BancoJson;
import tpaPOIs.Direccion;
import tpaPOIs.POI;
import tpaPOIs.ServicioExternoBancos;

public class BancoAdapterTest {

	private ServicioExternoBancos componenteBancos;
	private BancoAdapter bancoAdapter;
	private BancoJson unBancoJson;
	private Banco unBanco;

	@Before
	public void init() {

		componenteBancos = mock(ServicioExternoBancos.class);
		
		ArrayList<String> listaServicios = new ArrayList<String>() {
			{
				add("cobro cheques");
				add("depósitos");
				add("extracciones");
				add("transferencias");
				add("créditos");
				add("");
				add("");
				add("");
			}
		};

		unBancoJson = new BancoJson("Banco de la Plaza", -35.9338322, 72.348353, "Avellaneda", "Javier Loeschbor",
				listaServicios);

		List<BancoJson> listaBancoJson = new ArrayList<BancoJson>();

		listaBancoJson.add(unBancoJson);
		
		String json = new Gson().toJson(listaBancoJson);
		when(componenteBancos.buscar("Banco de la Plaza", "extracciones")).thenReturn(json);		
		String nombre = unBancoJson.getBanco();
		Point point = new Point(unBancoJson.getX(), unBancoJson.getY());
		Direccion direccion = null;
		ArrayList<String> etiquetas = unBancoJson.getServicios();

		unBanco = new Banco(point, nombre, direccion, etiquetas);

	}

	@Test
	public void SiLePidoAlComponenteLosBancosDisponiblesMeDevuelveUnBancoValido() {
		bancoAdapter = new BancoAdapter(componenteBancos);
		ArrayList<POI> listaBancos = bancoAdapter.buscarPoisExternos("Banco de la Plaza,extracciones");
		Banco bancoRecibido = (Banco) listaBancos.get(0);		
		
		Assert.assertTrue(unBanco.getNombre().equals(bancoRecibido.getNombre()));
		Assert.assertTrue(unBanco.getDireccion() == bancoRecibido.getDireccion());		
		Assert.assertTrue(unBanco.getEtiquetas().equals(bancoRecibido.getEtiquetas()));
		Assert.assertEquals(unBanco.getPosicion().latitude(),bancoRecibido.getPosicion().latitude(),0);
		Assert.assertEquals(unBanco.getPosicion().longitude(),bancoRecibido.getPosicion().longitude(),0);

	}

}
