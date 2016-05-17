package tpaPOIs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultorExterno {

	private static List<ComponenteExternoAdapter> adaptersComponentesExternos = new ArrayList<ComponenteExternoAdapter>();

	public static ArrayList<POI> damePoisExternos(String descripcion) {
		
		return (ArrayList<POI>) adaptersComponentesExternos.stream()
				.map(adapter-> adapter.buscarPoisExternos(descripcion))
				.flatMap(listaPois -> listaPois.stream()).collect(Collectors.toList());
	}
		
}
