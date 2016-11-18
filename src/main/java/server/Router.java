package server;

import controllers.ConsultasController;
import controllers.LoginController;
import controllers.PoisController;
import controllers.TerminalesController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.build();

		Spark.staticFiles.location("/public");
		
		//controllers
		
		PoisController poisController = new PoisController();
		TerminalesController terminalesController = new TerminalesController();
		ConsultasController consultasController = new ConsultasController();
		
		//rutas		
		
		Spark.get("/", LoginController::login, engine);
		Spark.post("/home/",LoginController::loginUsuario,engine);
		Spark.get("/terminal/:idTerminal/buscarPois/", poisController::buscarPois, engine);
		
		Spark.get("/terminal/:idTerminal/buscarPois/pois/:idPOI", poisController::mostrarPOI, engine);
		
		Spark.get("/admin/administrarPois/", poisController::administrarPois, engine);
		Spark.get("/admin/administrarPois/editarPOI/:id", poisController::editarPOI, engine);
		Spark.post("/admin/administrarPois/guardarPOI/:id",poisController::guardarPOI,engine);
		Spark.get("/admin/administrarPois/borrarPOI/:id", poisController::borrarPOI, engine);
		
		Spark.get("/admin/administrarTerminales/", terminalesController::administrarTerminales, engine);		
		Spark.get("/admin/administrarTerminales/editar/:id", terminalesController::mostrarTerminal, engine);
		Spark.get("/admin/administrarTerminales/agregar/", terminalesController::agregarTerminal, engine);
		Spark.get("/admin/administrarTerminales/eliminar/:id", terminalesController::eliminarTerminal, engine);
		Spark.post("/admin/administrarTerminales/guardarTerminalEditada/:id", terminalesController::editarTerminal, engine);
		Spark.post("/admin/administrarTerminales/guardarNuevaTerminal/", terminalesController::agregarNuevaTerminal, engine);
		
		Spark.get("/admin/administrarTerminales/:id/acciones/", terminalesController::mostrarAcciones, engine);
		Spark.post("/admin/administrarTerminales/:id/acciones/agregar",terminalesController::mostrarAgregarAccionATerminal ,engine);
		Spark.get("/admin/administrarTerminales/:id/acciones/borrar/:idAccion",terminalesController::borrarAccionDeTerminal ,engine);
		Spark.post("/admin/administrarTerminales/:id/acciones/",terminalesController::agregarAccionATerminal ,engine);
		Spark.post("/admin/administrarTerminales/:id/acciones/notificadorMail/",terminalesController::agregarNotificadorMailATerminal ,engine);
		
		Spark.get("/admin/historicoConsultas/", consultasController::historicoConsultas, engine);		
		Spark.get("/admin/historicoConsultas/poisDeConsulta/:id", poisController::verPoisDeConsulta,engine);
		
		
		
		
		
	}

}