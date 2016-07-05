package pois;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<Dispositivo> getListaDispositivos() {
		return listaDispositivos;
	}

	public void setListaDispositivos(List<Dispositivo> unaListaDeDispositivos) {
		listaDispositivos = unaListaDeDispositivos;
	}

	public void agregarDispositivo(Dispositivo dispositivo) {
		listaDispositivos.add(dispositivo);
	}

	public List<Dispositivo> filtrarPorComuna(Integer numComuna) throws Exception {
		Comuna comuna;
		try {
			comuna = cgpAdapter.dameComuna(numComuna);
		} catch (Exception e) {
			//Manejarlo
			throw new Exception("Numero de comuna invalido");
		}

		List<Dispositivo> dispositivos = listaDispositivos.stream()
				.filter(dispositivo -> comuna.incluyeA(dispositivo.getPosicion())).collect(Collectors.toList());
		return dispositivos;
	}
}
