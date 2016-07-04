package procesos;

import org.joda.time.DateTime;

public class ManejadorDeProcesos {
	
	private static ManejadorDeProcesos singleton;

	private ManejadorDeProcesos(){
		
	}
	
	public static ManejadorDeProcesos getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeProcesos();
		}

		return singleton;
	}
	
	
	public void configurarProceso(Proceso proceso,DateTime fechaYhoraDeEjecucion){
		
	}
	
}
