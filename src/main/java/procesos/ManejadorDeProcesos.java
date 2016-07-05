package procesos;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;

public class ManejadorDeProcesos {

	private static ManejadorDeProcesos singleton;

	ScheduledThreadPoolExecutor scheduler;

	private ManejadorDeProcesos() {
		this.scheduler = new ScheduledThreadPoolExecutor(1);
	}

	public static ManejadorDeProcesos getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeProcesos();
		}

		return singleton;
	}

	public void configurarProceso(Accion accion, Double frecuencia, DateTime fechaYhoraDeEjecucion) {
		// Agregar a una lista ordenada por horario de procesos

		Proceso procesoAEjecutar = new Proceso(accion, frecuencia, fechaYhoraDeEjecucion);
		DateTime fechaYhoraProcesoParaEjecutar = procesoAEjecutar.getFechaYhoraDeEjecucion();

		if (procesoAEjecutar.getFrecuenciaEnHoras() == 0) {

			scheduler.schedule(this.ejecutarProceso(procesoAEjecutar),
					fechaYhoraProcesoParaEjecutar.getMillis() - DateTime.now().getMillis(), TimeUnit.MILLISECONDS);

		} else {
			scheduler.scheduleAtFixedRate(this.ejecutarProceso(procesoAEjecutar),
					fechaYhoraProcesoParaEjecutar.getMillis() - DateTime.now().getMillis(),
					(long) (procesoAEjecutar.getFrecuenciaEnHoras() * 60 * 60 * 1000), TimeUnit.MILLISECONDS);
		}
	}

	private Runnable ejecutarProceso(Proceso proceso) {
		proceso.ejecutar();
		return null;
	}

}
