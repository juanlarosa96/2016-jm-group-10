package server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controllers.ConsultasController;
import controllers.LoginController;
import controllers.PoisController;
import controllers.TerminalesController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;
import usuarios.Usuario;

public class Router {

	private static List<Sesion> sesiones = new ArrayList<>();

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		Spark.staticFiles.location("/public");

		// controllers

		PoisController poisController = new PoisController();
		TerminalesController terminalesController = new TerminalesController();
		ConsultasController consultasController = new ConsultasController();

		// rutas

		Spark.get("/", LoginController::login, engine);

		Spark.post("/login/", LoginController::loginUsuario);
		
		Spark.get("/loginerror/:mensaje", LoginController::loginError, engine);

		Spark.get("/admin/", LoginController::homeAdmin, engine);
		Spark.get("/terminal/", LoginController::homeTerminal, engine);

		Spark.get("/terminal/:idTerminal/buscarPois/", poisController::buscarPois, engine);

		Spark.get("/terminal/:idTerminal/buscarPois/pois/:idPOI", poisController::mostrarPOI, engine);

		Spark.get("/admin/administrarPois/", poisController::administrarPois, engine);
		Spark.get("/admin/administrarPois/editarPOI/:id", poisController::editarPOI, engine);
		Spark.post("/admin/administrarPois/guardarPOI/:id", poisController::guardarPOI, engine);
		Spark.get("/admin/administrarPois/borrarPOI/:id", poisController::borrarPOI, engine);

		Spark.get("/admin/administrarTerminales/", terminalesController::administrarTerminales, engine);
		Spark.get("/admin/administrarTerminales/editar/:id", terminalesController::mostrarTerminal, engine);
		Spark.get("/admin/administrarTerminales/agregar/", terminalesController::agregarTerminal, engine);
		Spark.get("/admin/administrarTerminales/eliminar/:id", terminalesController::eliminarTerminal, engine);
		Spark.post("/admin/administrarTerminales/guardarTerminalEditada/:id", terminalesController::editarTerminal,
				engine);
		Spark.post("/admin/administrarTerminales/guardarNuevaTerminal/", terminalesController::agregarNuevaTerminal,
				engine);

		Spark.get("/admin/administrarTerminales/:id/acciones/", terminalesController::mostrarAcciones, engine);
		Spark.post("/admin/administrarTerminales/:id/acciones/agregar",
				terminalesController::mostrarAgregarAccionATerminal, engine);
		Spark.get("/admin/administrarTerminales/:id/acciones/borrar/:idAccion",
				terminalesController::borrarAccionDeTerminal, engine);
		Spark.post("/admin/administrarTerminales/:id/acciones/", terminalesController::agregarAccionATerminal, engine);
		Spark.post("/admin/administrarTerminales/:id/acciones/notificadorMail/",
				terminalesController::agregarNotificadorMailATerminal, engine);

		Spark.get("/admin/historicoConsultas/", consultasController::historicoConsultas, engine);
		Spark.get("/admin/historicoConsultas/poisDeConsulta/:id", poisController::verPoisDeConsulta, engine);

	}

	public static Usuario getUsuarioDeSesion(String idSesion) {
		return sesiones.stream().filter(sesion -> sesion.getId().equals(idSesion)).collect(Collectors.toList()).get(0)
				.getUsuario();
	}

	public static void iniciarSesion(String idSesion, Usuario usuario) {
		if (sesiones.stream().anyMatch(sesion -> sesion.getId().equals(idSesion)))
			sesiones.stream().filter(sesion -> sesion.getId().equals(idSesion)).collect(Collectors.toList()).get(0)
					.setUsuario(usuario);
		else
			sesiones.add(new Sesion(idSesion, usuario));
	}

}