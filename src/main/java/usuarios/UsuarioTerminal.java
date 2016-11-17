package usuarios;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;


import pois.Dispositivo;

@Entity
public class UsuarioTerminal extends Usuario {

	@OneToOne(fetch = FetchType.EAGER, orphanRemoval=true, cascade=CascadeType.ALL)
	private Dispositivo dispositivo;
	
	@Override
	public Boolean esAdmin() {
		return false;
	}
	
	@SuppressWarnings("unused") //constructor vacio para hibernate
	private UsuarioTerminal(){}
	
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
