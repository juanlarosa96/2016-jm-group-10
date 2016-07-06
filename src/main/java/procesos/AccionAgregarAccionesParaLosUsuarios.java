package procesos;

import java.util.List;

import org.joda.time.DateTime;

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
	public ResultadoEjecucion ejecutar() throws Exception {
		try{
			List<Dispositivo> usuarios = criterio.filtrar();
			usuarios.stream().forEach(usuario -> usuario.agregarInteresadoEnBusquedas(accionUsuario));
			return new ResultadoEjecucion(usuarios.size(),DateTime.now(),"Acciones agregadas para los usuarios seleccionados");
		}
		catch(Exception e){
			throw new Exception();
		}	
	}

}
