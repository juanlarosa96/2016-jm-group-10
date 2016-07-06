package procesos;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;

public class AccionEsperarUnSegundo implements Accion {

	@Override
	public ResultadoEjecucion ejecutar() throws ExceptionErrorEjecucionDeAccion {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ResultadoEjecucion(0, DateTime.now(), "Hola juli todo piola?");
	}

}
