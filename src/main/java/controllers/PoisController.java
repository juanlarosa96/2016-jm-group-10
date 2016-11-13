package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import pois.ManejadorDePois;
import pois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PoisController implements WithGlobalEntityManager, TransactionalOps {

	public ModelAndView buscarPois(Request req, Response res) {
		
		Map<String, List<POI>> model = new HashMap<>();
		
		List<POI> poisEncontrados = ManejadorDePois.getInstance().buscarPOIs(req.params("descripcion"));

		model.put("pois", poisEncontrados);
		
		return new ModelAndView(model, "pois/buscarPois.hbs");
	}

}
