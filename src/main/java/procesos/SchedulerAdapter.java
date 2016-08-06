package procesos;

import java.util.concurrent.TimeUnit;

public class SchedulerAdapter {

	private Scheduler scheduler;

	private static SchedulerAdapter singleton;

	public SchedulerAdapter() {

	}

	public void schedule(Proceso proceso, long tiempoFaltante, TimeUnit unidadTiempo, Double frecuencia) {
		scheduler.schedule(proceso, tiempoFaltante, unidadTiempo, frecuencia);
	}

	public static SchedulerAdapter getInstance() {

		if (singleton == null) {
			return new SchedulerAdapter();
		}
		return singleton;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}
