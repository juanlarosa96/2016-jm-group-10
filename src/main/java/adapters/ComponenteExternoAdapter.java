package adapters;

import java.util.ArrayList;

import pois.POI;

public interface ComponenteExternoAdapter {

	public ArrayList<POI> buscarPoisExternos(String descripcion);

}
