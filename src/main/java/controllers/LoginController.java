package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.ExceptionErrorLogin;
import usuarios.RepoUsuarios;
import usuarios.Usuario;

public class LoginController {

	public static ModelAndView login(Request req, Response res) {
		return new ModelAndView(null, "login/login.hbs");
	}

	public static ModelAndView loginUsuario(Request req, Response res) throws ExceptionErrorLogin {
		RepoUsuarios repoUsuarios = RepoUsuarios.getInstance();
		String body = req.body();
		String[] params = body.split("&");
		String username = params[0].split("=")[1];
		String password = params[1].split("=")[1];
		
		try {
			Usuario usuario = repoUsuarios.loginOK(username, password);

			if (usuario.esAdmin()) {
				Map<String, Usuario> model = new HashMap<>();

				model.put("usuario", usuario);
				
				return new ModelAndView(model, "admin/seleccionarPantallaAdmin.hbs");
			}

			else{
				Map<String, List<POI>> model = new HashMap<>();
				
				List<POI> pois = new ArrayList<POI>();
				
				model.put("pois", pois);
				
				return new ModelAndView(model, "terminal/buscarPois.hbs");
				}

		} catch (Exception e) {
			throw new ExceptionErrorLogin();
		}
	}
}
