package tpaPOIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManejadorDeReportes implements InteresadoEnBusquedas {

	List<Busqueda> busquedas;
	private static ManejadorDeReportes singleton;

	private ManejadorDeReportes() {

		busquedas = new ArrayList<Busqueda>();
	}

	@Override
	public void notificarBusqueda(Busqueda unaBusqueda) {
		busquedas.add(unaBusqueda);
	}

	public static ManejadorDeReportes getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeReportes();
		}

		return singleton;
	}

	public HashMap<String, Integer> generarReporteBusquedasPorFecha() {

		return null;

	}

	public HashMap<String, List<Integer>> generarReporteDeResultadosParcialesPorBusquedaPorTerminal() {
		return null;
	}
	
	public HashMap<String, Integer> generarReporteDeResultadoTotalesPorTerminales(){
		
		return null;
	}
}
