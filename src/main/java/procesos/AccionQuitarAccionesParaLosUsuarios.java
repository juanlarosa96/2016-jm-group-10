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
	public ResultadoEjecucion ejecutar() throws ExceptionErrorEjecucionDeAccion {
		try {

			List<Dispositivo> usuarios = criterio.filtrar();

			usuarios.stream().forEach(usuario -> usuario.eliminarInteresadoEnBusquedas(accionUsuario));

			return ResultadoEjecucion.dameResultadoConCantElemAfectadosYMensaje(usuarios.size(),
					"Acciones removidas para los usuarios seleccionados.");

		} catch (Exception e) {
			throw new ExceptionErrorEjecucionDeAccion();
		}
	}
}
