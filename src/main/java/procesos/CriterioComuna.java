package procesos;

import java.util.List;

import pois.Comuna;
import pois.Dispositivo;
import pois.ManejadorDeDispositivos;

public class CriterioComuna implements CriterioSeleccionUsuarios {

	private Comuna comuna;
	private ManejadorDeDispositivos manejadorDeDispositivos;

	public CriterioComuna(Comuna comuna) {
		this.comuna = comuna;
		this.manejadorDeDispositivos = ManejadorDeDispositivos.getInstance();
	}
	
	public List<Dispositivo> filtrar(){
		List<Dispositivo> dispositivos = manejadorDeDispositivos.filtrarPorComuna(comuna);
		return dispositivos;
	}

}
