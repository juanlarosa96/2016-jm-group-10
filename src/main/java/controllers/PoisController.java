package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import pois.ManejadorDePois;
import pois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PoisController implements WithGlobalEntityManager, TransactionalOps {

	public ModelAndView buscarPois(Request req, Response res) {

		String descripcion = req.queryParams("descripcion");

		List<POI> pois = ManejadorDePois.getInstance().buscarPOIs(descripcion);

		Map<String, List<POI>> model = new HashMap<>();

		model.put("pois", pois);

		return new ModelAndView(model, "terminal/buscarPois.hbs");
	}

	public ModelAndView administrarPois(Request req, Response res) {

		Map<String, List<POI>> model = new HashMap<>();

		String descripcion = req.queryParams("descripcion");
		String tipo = req.queryParams("tipo");

		List<POI> pois;

		if (descripcion != null && !descripcion.equals(""))
			pois = ManejadorDePois.getInstance().buscarPOIs(descripcion);

		else
			pois = ManejadorDePois.getInstance().traerTodosLosPois();

		if (tipo != null && !tipo.equals("Todos"))
			pois = pois.stream().filter(poi -> poi.getTipo().equals(tipo)).collect(Collectors.toList());

		model.put("pois", pois);

		return new ModelAndView(model, "admin/administrarPois.hbs");
	}

	public ModelAndView mostrarPOI(Request req, Response res) {
		Map<String, POI> model = new HashMap<>();
		String id = req.params("id");

		POI poi = ManejadorDePois.getInstance().getPOI(Long.parseLong(id));

		model.put("poi", poi);

		return new ModelAndView(model, "pois/mostrarPOI.hbs");
	}

	public ModelAndView editarPOI(Request req, Response res) {

		return null;
	}

	public ModelAndView borrarPOI(Request req, Response res) {

		return null;
	}

}
