package procesos;

import java.util.List;

import pois.Dispositivo;
import pois.ManejadorDeDispositivos;

public class CriterioComuna implements CriterioSeleccionUsuarios {

	private Integer numeroComuna;
	private ManejadorDeDispositivos manejadorDeDispositivos;

	public CriterioComuna(Integer comuna) {
		this.numeroComuna = comuna;
		this.manejadorDeDispositivos = ManejadorDeDispositivos.getInstance();
	}
	
	public List<Dispositivo> filtrar() throws Exception{
		List<Dispositivo> dispositivos = null;
		try {
			dispositivos = manejadorDeDispositivos.filtrarPorComuna(numeroComuna);
		} catch (Exception e) {
			throw new Exception();
		}
		return dispositivos;
	}

}
