package procesos;

import java.util.List;

import pois.Dispositivo;

public class CriterioUsuariosSeleccionados implements CriterioSeleccionUsuarios {

	private List<Dispositivo> usuarios;

	public CriterioUsuariosSeleccionados(List<Dispositivo> users) {
		this.usuarios = users;
	}

	@Override
	public List<Dispositivo> filtrar() {
		return this.usuarios;
	}

}
