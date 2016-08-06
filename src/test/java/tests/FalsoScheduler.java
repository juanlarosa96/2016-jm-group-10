package tests;

import java.util.concurrent.TimeUnit;

import procesos.Proceso;
import procesos.Scheduler;

public class FalsoScheduler implements Scheduler {

	private Integer cantVecesLlamado = 0;

	@Override
	public void schedule(Proceso proceso, long tiempoFaltante, TimeUnit unidadTiempo, Double frecuencia) {

		proceso.run();

		cantVecesLlamado++;

	}

	public Integer cantVecesLlamado() {
		return cantVecesLlamado;
	}

	public void resetLlamados() {
		cantVecesLlamado = 0;
	}

}
