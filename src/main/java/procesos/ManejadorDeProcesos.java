package procesos;


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
	
}
