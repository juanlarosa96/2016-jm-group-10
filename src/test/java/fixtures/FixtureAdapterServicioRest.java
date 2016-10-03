package fixtures;

import java.util.ArrayList;
import java.util.List;
import procesos.POIJson;

import com.google.gson.Gson;

public class FixtureAdapterServicioRest {
	
	private static POIJson unPOIJson;
	
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

	public static String dameURLPoisValidos() {
		return "URL";
	}

}
