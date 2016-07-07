package tests;

import java.util.Queue;

public class FalsoSchedulerThread implements Runnable {

	private FalsoScheduler scheduler;

	public FalsoSchedulerThread(FalsoScheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public void run() {

		Queue<Runnable> colaProcesos = scheduler.getColaProcesos();

		while (!colaProcesos.isEmpty()) {
			colaProcesos.poll().run();

			scheduler.aumentarCantProcesosEjecutados();
		}

	}

}
