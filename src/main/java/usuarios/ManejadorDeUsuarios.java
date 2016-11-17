package usuarios;

import eventosBusqueda.ManejadorDeReportes;
import herramientas.EntityManagerHelper;
import pois.Dispositivo;
import pois.Posicion;

public class ManejadorDeUsuarios {

	private static ManejadorDeUsuarios singleton = null;

	public void agregarUsuario(Usuario usuario) throws ExceptionUsernameYaExistente {
		
		if(EntityManagerHelper.existeUsuario(usuario.getUsername()))
			throw new ExceptionUsernameYaExistente();	
		else
			EntityManagerHelper.persistir(usuario);	
	}

	public Usuario loginOK(String username, String password) throws Exception {

		if(username.isEmpty() || username==null || password.isEmpty() || password==null){
			throw new Exception("Ingrese usuario y contraseña");
		}
		

		else if (!EntityManagerHelper.existeUsuario(username)) {
			throw new Exception("Usuario inexistente.");
		}

		else {
			Usuario usuario =  EntityManagerHelper.obtenerUsuario(username);

			if(!usuario.getPassword().equals(password))
				throw new Exception("Contraseña incorrecta.");
			else
				return usuario;
		}
	}

	public static ManejadorDeUsuarios getInstance() {
		if(singleton==null){
			singleton=new ManejadorDeUsuarios();
		}
		return singleton;
	}
	
	private ManejadorDeUsuarios(){
		
		Dispositivo disp = new Dispositivo("Terminal Prueba", new Posicion(-34.608365, -58.434501));
		disp.agregarInteresadoEnBusquedas(new ManejadorDeReportes());
		
		EntityManagerHelper.persistir(new UsuarioTerminal("pepe","argento",disp));
		EntityManagerHelper.persistir(new UsuarioAdmin("admin", "w23e"));
	}

}
