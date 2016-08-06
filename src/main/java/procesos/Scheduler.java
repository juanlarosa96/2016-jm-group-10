package procesos;

import java.util.concurrent.TimeUnit;

public interface Scheduler {

	void schedule(Proceso proceso, long tiempoFaltante, TimeUnit unidadTiempo, Double frecuencia);

}
