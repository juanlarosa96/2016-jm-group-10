package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import pois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PoisController implements WithGlobalEntityManager, TransactionalOps {

	public ModelAndView buscarPois(Request req, Response res) {
		
		Map<String, List<POI>> model = new HashMap<>();
				
		return new ModelAndView(model, "terminal/buscarPois.hbs");
	}
	
public ModelAndView administrarPois(Request req, Response res) {
		
		Map<String, List<String>> model = new HashMap<>();
		
		List<String> tipos = new ArrayList<String>();
		
		tipos.add("Banco");
		tipos.add("CGP");
		tipos.add("Comercio");		
		tipos.add("Parada Colectivo");
		
		model.put("tipos",tipos);
				
		return new ModelAndView(model, "admin/administrarPois.hbs");
	}

}
