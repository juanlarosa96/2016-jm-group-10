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
		Spark.get("/buscarPois/", poisController::buscarPois, engine);
		
		Spark.get("/buscarPois/pois/:id", poisController::mostrarPOI, engine);
		
		Spark.get("/admin/administrarPois/", poisController::administrarPois, engine);
		Spark.get("/admin/administrarTerminales/", terminalesController::administrarTerminales, engine);
//		Spark.get("/admin/administrarTerminales/acciones/:id", terminalesController::mostrarAcciones, engine);
//		Spark.get("/admin/administrarTerminales/editar/:id", terminalesController::editarTerminal, engine);
		Spark.get("/admin/administrarTerminales/agregar/", terminalesController::agregarTerminal, engine);
		Spark.get("/admin/administrarTerminales/eliminar/:id", terminalesController::eliminarTerminal, engine);
		Spark.get("/admin/historicoConsultas/", consultasController::historicoConsultas, engine);
		Spark.get("/admin/administrarPois/editarPOI/:id", poisController::editarPOI, engine);
		Spark.post("/admin/administrarPois/guardarPOI/:id",poisController::guardarPOI,engine);
		Spark.get("/admin/administrarPois/borrarPOI/:id", poisController::borrarPOI, engine);
		
		
		
		
		
	}

}