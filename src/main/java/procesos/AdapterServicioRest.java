package procesos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pois.Direccion;
import pois.ManejadorDePois;
import pois.POI;

public class AdapterServicioRest {
	
	private static AdapterServicioRest singleton = null;
	private ManejadorDePois manejadorDePois;
	private ServicioRest servicioRest;
	
	private AdapterServicioRest() {
		this.manejadorDePois = ManejadorDePois.getInstance();

	}
	
	public static AdapterServicioRest getInstance() {
		if (singleton == null) {
			singleton = new AdapterServicioRest();
		}

		return singleton;
	}
	
	public ArrayList<POI> parsearPOISJson(String urlPoisAEliminar) {
		
		String stringJson = servicioRest.buscarPOISDadosDeBaja(urlPoisAEliminar);
		
		ArrayList<POIJson> listaJson = new ArrayList<POIJson>();
		ArrayList<POI> listaPois;
		Type tipoListaPOISJson = new TypeToken<Collection<POIJson>>(){}.getType();
		Gson parserGson = new Gson();
		
		listaJson = parserGson.fromJson(stringJson,tipoListaPOISJson);
		
		listaPois = (ArrayList<POI>) listaJson.stream().map(POIJson-> convertirAPOI(POIJson)).collect(Collectors.toList());
		
		
		return listaPois;
	}
	
	private POI convertirAPOI(POIJson poiJson) {
		
		String nombre = poiJson.getNombre();
		String calle = poiJson.getCalle();
		Integer altura = poiJson.getAltura();
		
		Direccion direccion = new Direccion(calle, altura, null,null,null,null,null,null,null,null,null);
		POI unPoi = manejadorDePois.buscarPOI(nombre, direccion);
						 
		return unPoi;
		
	}

	public void setServicioRest(ServicioRest servicioRest) {
		this.servicioRest = servicioRest;
	}

	

}
