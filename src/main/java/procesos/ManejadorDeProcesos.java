package procesos;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import org.joda.time.DateTime;

public class ManejadorDeProcesos implements Runnable {
	
	private static ManejadorDeProcesos singleton;
	private List<Proceso> procesos;

	private ManejadorDeProcesos(){
		
	}
	
	public static ManejadorDeProcesos getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeProcesos();
		}

		return singleton;
	}
	
	
	public void configurarProceso(Accion accion, Double frecuencia,DateTime fechaYhoraDeEjecucion){
		//Agregar a una lista ordenada por horario de procesos
		procesos.add(new Proceso(accion, frecuencia, fechaYhoraDeEjecucion));
	}
	
	@Override
	public void run() {
		//Verificar el horario y ejecutar el proceso si corresponde
	}
	
	public static void main(String[] args) {
		

	}

}

