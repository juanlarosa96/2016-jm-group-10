package pois;

import java.util.List;
import java.util.stream.Collectors;

import adapters.CgpAdapter;
import herramientas.EntityManagerHelper;

public class ManejadorDeDispositivos {
	private static ManejadorDeDispositivos singleton = null;
	public List<Dispositivo> listaDispositivos;
	private CgpAdapter cgpAdapter;

	@SuppressWarnings("unchecked")
	private ManejadorDeDispositivos() {
		EntityManagerHelper.beginTransaction();

		listaDispositivos = EntityManagerHelper.createQuery("from Dispositivo").getResultList();

		EntityManagerHelper.commit();
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

		EntityManagerHelper.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Dispositivo> dispViejos = EntityManagerHelper.createQuery("from Dispositivo").getResultList();
		dispViejos.stream().forEach(disp -> EntityManagerHelper.remove(disp));

		nuevaListaDispositivos.stream().forEach(poi -> EntityManagerHelper.persist(poi));

		EntityManagerHelper.commit();
	}

	public void agregarDispositivo(Dispositivo dispositivo) {
		listaDispositivos.add(dispositivo);

		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.persist(dispositivo);
		EntityManagerHelper.commit();
	}

	public List<Dispositivo> filtrarPorComuna(Integer numComuna) throws Exception {
		Comuna comuna;
		try {
			comuna = cgpAdapter.dameComuna(numComuna);
		} catch (Exception e) {
			throw new Exception("Numero de comuna invalido");
		}

		List<Dispositivo> dispositivos = this.listaDispositivos.stream()
				.filter(dispositivo -> comuna.incluyeA(dispositivo.getPosicion())).collect(Collectors.toList());
		return dispositivos;
	}

	public void eliminarDispositivo(Dispositivo disp) {
		listaDispositivos.remove(disp);
		
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.find(Dispositivo.class, disp.getId());
		EntityManagerHelper.remove(disp);
		EntityManagerHelper.commit();
				
	}
}
