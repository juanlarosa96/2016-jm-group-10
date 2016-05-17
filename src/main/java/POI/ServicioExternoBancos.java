package POI;

import java.util.HashMap;

public interface ServicioExternoBancos {

	HashMap<String,String> buscar(String banco, String servicio);

}
