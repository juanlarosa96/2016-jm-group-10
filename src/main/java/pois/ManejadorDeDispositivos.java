package pois;

import java.util.List;

public class ManejadorDeDispositivos {
	private static ManejadorDeDispositivos singleton = null;
	public static List<Dispositivo> listaDispositivos;
	
	private ManejadorDeDispositivos() {

	}

	public static ManejadorDeDispositivos getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeDispositivos();
		}

		return singleton;
	}

	public void setListaDispositivos(List<Dispositivo> unaListaDeDispositivos) {
		listaDispositivos = unaListaDeDispositivos;
	}
	
	public void agregarDispositivo(Dispositivo dispositivo) {
		listaDispositivos.add(dispositivo);
	}
}
