package usuarios;

import pois.Dispositivo;

public class UsuarioTerminal extends Usuario {

	private Dispositivo dispositivo;
	
	@Override
	public Boolean esAdmin() {
		return false;
	}
	
	public UsuarioTerminal(String username, String password, Dispositivo dispositivo){
		this.setUsername(username);
		this.setPassword(password);
		this.setDispositivo(dispositivo);
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}	

}
