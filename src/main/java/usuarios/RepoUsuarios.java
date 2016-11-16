package usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eventosBusqueda.ManejadorDeReportes;
import pois.Dispositivo;
import pois.Posicion;

public class RepoUsuarios {

	private static RepoUsuarios singleton = null;
	private List<Usuario> usuarios;

	public void agregarUsuario(Usuario usuario) throws ExceptionUsernameYaExistente {
		
		List<Usuario> usuariosQueMatchean = this.usuarios.stream()
				.filter(unUsuario -> unUsuario.getUsername() == usuario.getUsername())
				.collect(Collectors.toList());
		
		if (!usuariosQueMatchean.isEmpty()) {
			throw new ExceptionUsernameYaExistente();
		}
		
		this.usuarios.add(usuario);
	}

	public Usuario loginOK(String username, String password) throws Exception {

		if(username.isEmpty() || username==null || password.isEmpty() || password==null){
			throw new Exception("Ingrese usuario y contraseña");
		}
		
		List<Usuario> usuariosQueMatchean = this.usuarios.stream()
				.filter(usuario -> usuario.getUsername().equals(username))
				.collect(Collectors.toList());

		if (usuariosQueMatchean.size() > 1) {
			throw new Exception("Hay más de un usuario con ese username.");
		}

		else if (usuariosQueMatchean.isEmpty()) {
			throw new Exception("Usuario inexistente.");
		}

		else {
			Usuario usuario = usuariosQueMatchean.get(0);

			if(!usuario.getPassword().equals(password))
				throw new Exception("Contraseña incorrecta.");
			else
				return usuario;
		}
	}

	public static RepoUsuarios getInstance() {
		if(singleton==null){
			singleton=new RepoUsuarios();
		}
		return singleton;
	}
	
	private RepoUsuarios(){
		
		Dispositivo disp = new Dispositivo("Terminal Prueba", new Posicion(-34.608365, -58.434501));
		disp.agregarInteresadoEnBusquedas(new ManejadorDeReportes());
		
		this.usuarios = new ArrayList<Usuario>();
		this.usuarios.add(new UsuarioTerminal("pepe","argento",disp));
		this.usuarios.add(new UsuarioAdmin("admin", "w23e"));
	}

}
