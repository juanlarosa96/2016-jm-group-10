package fixtures;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import adapters.BancoJson;

import com.google.gson.Gson;

import procesos.POIJson;

public class FixtureAdapterServicioRest {
	
	private static POIJson unPOIJson;
	private static DateTime unHorarioCualquiera = new DateTime(2016, 4, 5, 2, 30);

	private static void inicializarPOIJson() {
	

		unPOIJson = new POIJson("114", "Av. Segurola", 230 /*, unHorarioCualquiera */);
		
		return;
		
	}
	
	public static String devolverListaPOIJsonNoVacia() {

		FixtureAdapterServicioRest.inicializarPOIJson();		
		List<POIJson> listaPOIJson = new ArrayList<POIJson>();

		listaPOIJson.add(unPOIJson);

		return new Gson().toJson(listaPOIJson);

	}

}
