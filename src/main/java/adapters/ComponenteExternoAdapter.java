package adapters;

import java.util.ArrayList;
import java.util.List;

import pois.POI;

public interface ComponenteExternoAdapter {

	public ArrayList<POI> buscarPoisExternos(String descripcion);

	public List<POI> buscarPoisDadosDeBaja(String urlPoisAEliminar);

}
