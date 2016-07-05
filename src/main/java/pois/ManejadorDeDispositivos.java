package pois;

import java.util.List;

import adapters.CgpAdapter;

public class ManejadorDeDispositivos {
	private static ManejadorDeDispositivos singleton = null;
	public List<Dispositivo> listaDispositivos;
	private CgpAdapter cgpAdapter;
	
	private ManejadorDeDispositivos() {

	}

	public static ManejadorDeDispositivos getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeDispositivos();
		}

		return singleton;
	}

	public void setCgpAdapter(CgpAdapter cgpAdapter) {
		this.cgpAdapter = cgpAdapter;
	}

	public void setListaDispositivos(List<Dispositivo> unaListaDeDispositivos) {
		listaDispositivos = unaListaDeDispositivos;
	}

	public void agregarDispositivo(Dispositivo dispositivo) {
		listaDispositivos.add(dispositivo);
	}

	public List<Dispositivo> filtrarPorComuna(Comuna comuna) {
		// TODO Auto-generated method stub
		return null;
	}
}
