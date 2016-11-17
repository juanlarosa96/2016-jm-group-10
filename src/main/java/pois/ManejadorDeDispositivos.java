package pois;

import java.util.List;
import java.util.stream.Collectors;

import adapters.CgpAdapter;
import herramientas.EntityManagerHelper;

public class ManejadorDeDispositivos {
	private static ManejadorDeDispositivos singleton = null;
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
		return EntityManagerHelper.traerTodosLosDispositivos();
	}

	public void setListaDispositivos(List<Dispositivo> nuevaListaDispositivos) {
		EntityManagerHelper.actualizarDispositivos(nuevaListaDispositivos);
	}

	public void agregarDispositivo(Dispositivo dispositivo) {
		EntityManagerHelper.persistir(dispositivo);
	}

	public List<Dispositivo> filtrarPorComuna(Integer numComuna) throws ExceptionComunaInvalida {
		Comuna comuna;
		try {
			comuna = cgpAdapter.dameComuna(numComuna);
		} catch (Exception e) {
			throw new ExceptionComunaInvalida();
		}

		List<Dispositivo> todosLosDispositivos = this.getListaDispositivos();

		List<Dispositivo> dispositivosFiltrados = todosLosDispositivos.stream()
				.filter(dispositivo -> comuna.incluyeA(dispositivo.getPosicion())).collect(Collectors.toList());

		return dispositivosFiltrados;
	}

	public void eliminarDispositivo(Dispositivo disp) {
		EntityManagerHelper.removerDispositivo(disp.getId());
	}

	public void clearDispositivos() {
		EntityManagerHelper.removerTodosLosDispositivos();
	}

	public Dispositivo getDispositivo(Integer id) {
		return this.getListaDispositivos().stream().filter(d -> d.getId().equals(id)).collect(Collectors.toList())
				.get(0);
	}

	public void actualizarDispositivo(Dispositivo dispositivo) {

		EntityManagerHelper.actualizarDispositivo(dispositivo);
	}
}
