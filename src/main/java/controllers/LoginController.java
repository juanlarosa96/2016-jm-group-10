package controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.ExceptionErrorLogin;
import usuarios.RepoUsuarios;
import usuarios.Usuario;

public class LoginController {

	public static ModelAndView login(Request req, Response res){
		return new ModelAndView(null, "login/login.hbs");
	}
	
	public static ModelAndView loginUsuario(Request req, Response res) throws ExceptionErrorLogin{
		RepoUsuarios repoUsuarios = RepoUsuarios.getInstance();
		String username = req.params("username");		
		String password = req.params("password");
		
		try {
			Usuario usuario = repoUsuarios.loginOK(username, password);
			
			Map<String, Usuario> model = new HashMap<>();
			
			model.put("usuario",usuario);
			
			if(usuario.esAdmin())	
				return new ModelAndView(model, "admin/seleccionarPantallaAdmin.hbs");		
			
			else
				return new ModelAndView(model, "terminal/buscarPois.hbs");			
			
		} catch (Exception e) {
			throw new ExceptionErrorLogin();
		}
	}
}
