package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pois.Banco;
import pois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.RepoUsuarios;
import usuarios.Usuario;
import usuarios.UsuarioTerminal;

public class LoginController {

	public static ModelAndView login(Request req, Response res) {
		return new ModelAndView(null, "login/login.hbs");
	}

	public static ModelAndView loginUsuario(Request req, Response res){
		RepoUsuarios repoUsuarios = RepoUsuarios.getInstance();
		String body = req.body();
		String[] params = body.split("&");
		
		String username; 
		String password;
		
		if(params[0].split("=").length < 2)
			username="";
		else
			username= params[0].split("=")[1];
		
		if(params[1].split("=").length < 2)
			password="";
		else
			password = params[1].split("=")[1];
		
		try {
			Usuario usuario = repoUsuarios.loginOK(username, password);

			if (usuario.esAdmin()) {
				Map<String, Usuario> model = new HashMap<>();

				model.put("usuario", usuario);
				
				return new ModelAndView(model, "admin/seleccionarPantallaAdmin.hbs");
			}

			else{
				
				UsuarioTerminal usuarioTerminal = (UsuarioTerminal) usuario;
				String idTerminal = usuarioTerminal.getDispositivo().getId().toString();
				
				Map<String, List<POI>> model = new HashMap<>();
				
				List<POI> pois = new ArrayList<POI>();				
				model.put("pois", pois);
				
				List<POI> poiConIDTerminalComoNombre = new ArrayList<POI>();
				poiConIDTerminalComoNombre.add(new Banco(null,idTerminal,null,null));
				model.put("terminales", poiConIDTerminalComoNombre);				
				
				return new ModelAndView(model, "terminal/buscarPois.hbs");
				}

		} catch (Exception e) {
			Map<String, String> model = new HashMap<>();
			model.put("excepcion", e.getMessage());
			return new ModelAndView(model, "login/loginerror.hbs");
		}
	}
}
