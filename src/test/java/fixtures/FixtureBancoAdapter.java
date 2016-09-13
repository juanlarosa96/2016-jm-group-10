package fixtures;

import java.util.ArrayList;
import java.util.List;

import pois.Posicion;

import com.google.gson.Gson;

import adapters.BancoJson;
import pois.Banco;
import pois.Direccion;

public class FixtureBancoAdapter {

	private static BancoJson unBancoJson;

	private static void inicializarBancoJson() {
		
		ArrayList<String> listaServicios = new ArrayList<String>() {
			{
				add("cobro cheques");
				add("depositos");
				add("extracciones");
				add("transferencias");
				add("creditos");
				add("");
				add("");
				add("");
			}
		};

		unBancoJson = new BancoJson("Banco de la Plaza", -35.9338322, 72.348353, "Avellaneda", "Javier Loeschbor",
				listaServicios);
		
		return;
		
	}
	
	public static String devolverListaBancoJsonNoVacia() {

		FixtureBancoAdapter.inicializarBancoJson();		
		List<BancoJson> listaBancoJson = new ArrayList<BancoJson>();

		listaBancoJson.add(unBancoJson);

		return new Gson().toJson(listaBancoJson);

	}
	
	public static String devolverListaBancoJsonVacia() {
		List<BancoJson> listaVacia = new ArrayList<BancoJson>();
		
		return new Gson().toJson(listaVacia);		
		
	}

	public static Banco devolverBancoValido() {
		
		FixtureBancoAdapter.inicializarBancoJson();
		String nombre = unBancoJson.getBanco();
		Posicion point = new Posicion(unBancoJson.getX(), unBancoJson.getY());
		Direccion direccion = null;
		ArrayList<String> etiquetas = unBancoJson.getServicios();

		return new Banco(point, nombre, direccion, etiquetas);

	}

	

}
