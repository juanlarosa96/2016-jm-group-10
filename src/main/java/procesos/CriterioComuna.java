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
	
	public List<Dispositivo> filtrar(){
		List<Dispositivo> dispositivos = manejadorDeDispositivos.filtrarPorComuna(numeroComuna);
		return dispositivos;
	}

}
