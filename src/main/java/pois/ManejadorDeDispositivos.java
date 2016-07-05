package pois;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import adapters.CgpAdapter;

public class ManejadorDeDispositivos {
	private static ManejadorDeDispositivos singleton = null;
	public List<Dispositivo> listaDispositivos;
	
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

	public List<Dispositivo> filtrarPorComuna(Comuna comuna) {
		List<Dispositivo> dispositivos = listaDispositivos.stream().filter(dispositivo -> comuna.incluyeA(dispositivo.getPosicion())).collect(Collectors.toList());
		return dispositivos;
	}
}
