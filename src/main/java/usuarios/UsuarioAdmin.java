package usuarios;

import javax.persistence.Entity;

@Entity
public class UsuarioAdmin extends Usuario {
	
	@SuppressWarnings("unused") //constructor vacio para hibernate
	private UsuarioAdmin(){}
	
	@Override
	public Boolean esAdmin() {
		return true;
	}
	
	public UsuarioAdmin(String username, String password){
		this.setUsername(username);
		this.setPassword(password);
	}

}