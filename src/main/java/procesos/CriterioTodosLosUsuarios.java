package procesos;

import java.util.List;

import pois.Dispositivo;
import pois.ManejadorDeDispositivos;

public class CriterioTodosLosUsuarios implements CriterioSeleccionUsuarios {

	private ManejadorDeDispositivos manejadorDeDispositivos;
	
	public CriterioTodosLosUsuarios() {
		this.manejadorDeDispositivos = ManejadorDeDispositivos.getInstance();
	}
	
	@Override
	public List<Dispositivo> filtrar() {
		return manejadorDeDispositivos.getListaDispositivos();
	}

}
