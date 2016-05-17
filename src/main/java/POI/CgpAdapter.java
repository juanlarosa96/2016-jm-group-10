package POI;

import java.util.ArrayList;

public class CgpAdapter implements ComponenteExternoAdapter {

	ServicioExternoCGP servicioExternoCgp;
	
	@Override
	public ArrayList<POI> buscarPoisExternos(String descripcion) {
		return this.adaptarCGPs(servicioExternoCgp.buscar(descripcion));
	}
	
	private ArrayList<POI> adaptarCGPs(CentroDTO cgps) {
		
	}
}
