package server;

import controllers.LoginController;
import controllers.PoisController;
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
		
		//rutas		
		
		Spark.get("/", LoginController::login, engine);
		Spark.post("/", LoginController::loginUsuario, engine);
		Spark.get("/buscarPois", poisController::buscarPois, engine);
		Spark.get("/administrarPois", poisController::administrarPois, engine);
		
		
	}

}