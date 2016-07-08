package procesos;

import java.util.List;

import eventosBusqueda.InteresadoEnBusquedas;
import pois.Dispositivo;

public class AccionAgregarAccionesParaLosUsuarios implements Accion {

	private InteresadoEnBusquedas accionUsuario;
	private CriterioSeleccionUsuarios criterio;

	public AccionAgregarAccionesParaLosUsuarios(InteresadoEnBusquedas accion, CriterioSeleccionUsuarios criterio) {
		this.accionUsuario = accion;
		this.criterio = criterio;
	}

	@Override
	public ResultadoEjecucion ejecutar() throws ExceptionErrorEjecucionDeAccion {
		try {
			
			List<Dispositivo> usuarios = criterio.filtrar();
			
			usuarios.stream().forEach(usuario -> usuario.agregarInteresadoEnBusquedas(accionUsuario));
			
			return ResultadoEjecucion.dameResultadoCon(usuarios.size(),
					"Acciones agregadas para los usuarios seleccionados");
			
		} catch (Exception e) {
			throw new ExceptionErrorEjecucionDeAccion();
		}
	}
}
