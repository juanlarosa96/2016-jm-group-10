package procesos;

import java.util.List;

import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.List;

import adapters.ComponenteExternoAdapter;
import pois.ManejadorDePois;
import pois.POI;


public class AccionDarDeBajaPOI implements Accion {

	private ManejadorDePois manejadorDePois;
	private String urlPoisAEliminar;
	private AdapterServicioRest adapterServicioRest;

	@Override
	public ResultadoEjecucion ejecutar() throws ExceptionErrorEjecucionDeAccion {
		try {

			ArrayList<POI> poisAEliminar = adapterServicioRest.buscarPoisDadosDeBaja(urlPoisAEliminar);

			poisAEliminar.stream().forEach(poi -> manejadorDePois.eliminarPOI(poi));

			return new ResultadoEjecucion(poisAEliminar.size(), DateTime.now(), "POIs dados de baja correctamente.");

		} catch (Exception e) {
			throw new ExceptionErrorEjecucionDeAccion();
		}
	}

	public AccionDarDeBajaPOI(String urlPoisAEliminar) {

		this.manejadorDePois = ManejadorDePois.getInstance();
		this.urlPoisAEliminar = urlPoisAEliminar;

	}
}
