package usuarios;

public class Usuario {

	private String username;
	private String password;
	private Boolean esAdmin;
	
	
	public static Usuario nuevoUsuarioTerminal(String username, String password){
		return new Usuario(username,password,false);
	}
	
	public static Usuario nuevoUsuarioAdministrador(String username, String password){
		return new Usuario(username,password,true);
	}
	
	private Usuario(String username, String password, Boolean esAdmin){
		this.username=username;
		this.password=password;
		this.esAdmin=esAdmin;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean esAdmin(){
		return this.esAdmin;
	}
	
}
