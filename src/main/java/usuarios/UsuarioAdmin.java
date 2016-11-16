package usuarios;


public class UsuarioAdmin extends Usuario {
	
	@Override
	public Boolean esAdmin() {
		return true;
	}
	
	public UsuarioAdmin(String username, String password){
		this.setUsername(username);
		this.setPassword(password);
	}

}