package procesos;

import java.util.List;

import eventosBusqueda.InteresadoEnBusquedas;
import pois.Dispositivo;

public class AccionQuitarAccionesParaLosUsuarios implements Accion {

	private InteresadoEnBusquedas accionUsuario;
	private CriterioSeleccionUsuarios criterio;
	
	public AccionQuitarAccionesParaLosUsuarios(InteresadoEnBusquedas accion, CriterioSeleccionUsuarios criterio) {
		this.accionUsuario = accion;
		this.criterio = criterio;
	}

	@Override
	public void ejecutar() throws Exception {
		try{
			List<Dispositivo> usuarios = criterio.filtrar();
			usuarios.stream().forEach(usuario -> usuario.eliminarInteresadoEnBusquedas(accionUsuario));
		}
		catch(Exception e){
			throw new Exception();
		}	
	}
}
