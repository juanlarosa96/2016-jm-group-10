package procesos;

import java.util.ArrayList;

import pois.ManejadorDePois;
import pois.POI;

public class AccionDarDeBajaPOI implements Accion {

	private ManejadorDePois manejadorDePois;
	private String urlPoisAEliminar;
	private AdapterServicioRest adapterServicioRest;

	@Override
	public ResultadoEjecucion ejecutar() throws ExceptionErrorEjecucionDeAccion {
		try {

			ArrayList<POI> poisAEliminar = adapterServicioRest.parsearPOISJson(urlPoisAEliminar);

			poisAEliminar.stream().forEach(poi -> manejadorDePois.eliminarPOIInterno(poi));

			return ResultadoEjecucion.dameResultadoConCantElemAfectadosYMensaje(poisAEliminar.size(), "POIs dados de baja correctamente.");

		} catch (Exception e) {
			throw new ExceptionErrorEjecucionDeAccion();
		}
	}

	public AccionDarDeBajaPOI(String urlPoisAEliminar) {

		this.manejadorDePois = ManejadorDePois.getInstance();
		this.urlPoisAEliminar = urlPoisAEliminar;

	}
}
