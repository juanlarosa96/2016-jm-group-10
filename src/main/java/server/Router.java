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
		Spark.get("/login/", LoginController::loginUsuario, engine);
		Spark.get("/buscarPois/", poisController::buscarPois, engine);
		
		Spark.get("/buscarPois/pois/:id", poisController::mostrarPOI, engine);
		
		Spark.get("/admin/administrarPois/", poisController::administrarPois, engine);
		Spark.get("/admin/administrarTerminales/", terminalesController::administrarTerminales, engine);
		Spark.get("/admin/historicoConsultas/", consultasController::historicoConsultas, engine);
		Spark.get("/admin/editarPOI/:id", poisController::editarPOI, engine);
		
		
		
		
	}

}