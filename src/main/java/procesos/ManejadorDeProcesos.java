package procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;

public class ManejadorDeProcesos {

	private static ManejadorDeProcesos singleton = null;
	private SchedulerAdapter schedulerAdapter;
	private List<Proceso> procesosEjecutados;

	public List<Proceso> getProcesosEjecutados() {

		return this.procesosEjecutados;

	}

	private ManejadorDeProcesos() {
		this.schedulerAdapter = SchedulerAdapter.getInstance();
		this.procesosEjecutados = new ArrayList<Proceso>();
	}

	public static ManejadorDeProcesos getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeProcesos();
		}

		return singleton;
	}

	public void configurarProceso(Accion accion, Double frecuencia, DateTime fechaYhoraDeEjecucion,
			CriterioDeManejoDeError criterioError) {

		Proceso procesoAEjecutar = new Proceso(accion, frecuencia, fechaYhoraDeEjecucion, criterioError);
		DateTime fechaYhoraProcesoParaEjecutar = procesoAEjecutar.getFechaYhoraDeEjecucion();

		schedulerAdapter.schedule(procesoAEjecutar,
				fechaYhoraProcesoParaEjecutar.getMillis() - DateTime.now().getMillis(), TimeUnit.MILLISECONDS, frecuencia);

	}

	public void agregarProcesoEjecutado(Proceso proceso) {
		this.procesosEjecutados.add(proceso);
	}

	public void vaciarListaProcesosEjecutados() {
		this.procesosEjecutados.clear();
	}

	public Integer cantProcesosEjecutados() {
		return this.procesosEjecutados.size();
	}

}
