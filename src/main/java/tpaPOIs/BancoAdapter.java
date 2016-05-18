package tpaPOIs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class BancoAdapter implements ComponenteExternoAdapter {

	ServicioExternoBancos servicioExternoBancos;
		
	@Override
	public ArrayList<POI> buscarPoisExternos(String descripcion) {
		
		
		String[] parametros = descripcion.split(",", 2);
		
		return this.adaptarBancos(servicioExternoBancos.buscar(parametros[0],parametros[1]));		
	}

	private ArrayList<POI> adaptarBancos(String json) {
		// aca adaptar con el parser
		
		ArrayList<BancoJson> listaJson = new ArrayList<BancoJson>();
		ArrayList<POI> listaBancos;
		Type tipoListaBancosJson = new TypeToken<Collection<POI>>(){}.getType();
		Gson parserGson = new Gson();
		
		listaJson = parserGson.fromJson(json,tipoListaBancosJson);
		
		listaBancos = (ArrayList<POI>) listaJson.stream().map(bancoJson-> convertirABanco(bancoJson)).collect(Collectors.toList());
		
		
		return listaBancos;
	}

	private POI convertirABanco(BancoJson bancoJson) {
		
		String nombre = bancoJson.getBanco();
		Point point = new Point(bancoJson.getX(),bancoJson.getY());
		Direccion direccion = null;		
		ArrayList <String> etiquetas = bancoJson.getServicios();
		
		 
		return new Banco(point,nombre,direccion, etiquetas);
		
	}

}
