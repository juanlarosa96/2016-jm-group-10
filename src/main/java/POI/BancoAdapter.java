package POI;

import java.util.ArrayList;
import java.util.HashMap;



public class BancoAdapter implements ComponenteExternoAdapter {

	ServicioExternoBancos servicioExternoBancos;
		
	@Override
	public ArrayList<POI> buscarPoisExternos(String descripcion) {
		
		
		String[] parametros = descripcion.split(",", 2);
		
		return this.adaptarBancos(servicioExternoBancos.buscar(parametros[0],parametros[1]));
		
		
	}

	private ArrayList<POI> adaptarBancos(HashMap<String, String> hashMap) {
		// aca adaptar con el parser
		return null;
	}

}
