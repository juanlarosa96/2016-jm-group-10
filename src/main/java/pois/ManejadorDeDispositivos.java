package pois;

import java.util.List;
import java.util.stream.Collectors;

import adapters.CgpAdapter;
import herramientas.EntityManagerHelper;

public class ManejadorDeDispositivos {
	private static ManejadorDeDispositivos singleton = null;
	public List<Dispositivo> listaDispositivos;
	private CgpAdapter cgpAdapter;

	private ManejadorDeDispositivos() {
		listaDispositivos = EntityManagerHelper.traerTodosLosDispositivos();
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

	public void setListaDispositivos(List<Dispositivo> nuevaListaDispositivos) {
		listaDispositivos = nuevaListaDispositivos;

		EntityManagerHelper.actualizarDispositivos(nuevaListaDispositivos);
	}

	public void agregarDispositivo(Dispositivo dispositivo) {
		listaDispositivos.add(dispositivo);
		EntityManagerHelper.persistir(dispositivo);
	}

	public List<Dispositivo> filtrarPorComuna(Integer numComuna) throws ExceptionComunaInvalida{
		Comuna comuna;
		try {
			comuna = cgpAdapter.dameComuna(numComuna);
		} catch (Exception e) {
			throw new ExceptionComunaInvalida();
		}

		List<Dispositivo> dispositivos = this.listaDispositivos.stream()
				.filter(dispositivo -> comuna.incluyeA(dispositivo.getPosicion())).collect(Collectors.toList());
		return dispositivos;
	}

	public void eliminarDispositivo(Dispositivo disp) {
		listaDispositivos.remove(disp);
		
		EntityManagerHelper.removerDispositivo(disp.getId());
	}

	public void clearDispositivos() {
		listaDispositivos.clear();
		
		EntityManagerHelper.removerTodosLosDispositivos();
	}

	public Dispositivo getDispositivo(Integer id) {
		return listaDispositivos.stream().filter(d -> d.getId().equals(id)).collect(Collectors.toList()).get(0);
	}

	public void actualizarDispositivo(Dispositivo dispositivo) {
		Dispositivo dispositivoViejo = listaDispositivos.stream().filter(disp -> disp.getId().equals(dispositivo.getId())).findFirst().get();

		listaDispositivos.remove(dispositivoViejo);
		listaDispositivos.add(dispositivo);

		EntityManagerHelper.actualizarDispositivo(dispositivo);
	}
}
