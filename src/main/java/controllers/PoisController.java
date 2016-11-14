package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import pois.Direccion;
import pois.ManejadorDePois;
import pois.POI;
import pois.Posicion;
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

		Map<String, String> model = new HashMap<>();

		String id = req.params("id");

		POI poi = ManejadorDePois.getInstance().getPOI(Long.parseLong(id));

		model.put("nombre", poi.getNombre());

		Direccion direccion = poi.getDireccion();

		if (direccion.getCalle() != null)
			model.put("calle", direccion.getCalle());
		
		if (direccion.getAltura() != null)
			model.put("altura", direccion.getAltura().toString());
		
		if (direccion.getPiso() != null)
			model.put("piso", direccion.getPiso().toString());
		
		if (direccion.getDepartamento() != null)
			model.put("departamento", direccion.getDepartamento().toString());
		
		if (direccion.getEntreCalle1() != null)
			model.put("entreCalle1", direccion.getEntreCalle1());
		
		if (direccion.getEntreCalle2() != null)
			model.put("entreCalle2", direccion.getEntreCalle2());
		if (direccion.getCodigoPostal() != null)
			model.put("codigoPostal", direccion.getCodigoPostal().toString());
		
		if (direccion.getLocalidad() != null)
			model.put("localidad", direccion.getLocalidad());
		
		if (direccion.getBarrio() != null)
			model.put("barrio", direccion.getBarrio());
		
		if (direccion.getProvincia() != null)
			model.put("provincia", direccion.getProvincia());
		
		if (direccion.getPais() != null)
			model.put("pais", direccion.getPais());
		
		Posicion posicion = poi.getPosicion();
		
		model.put("coordX", String.valueOf(posicion.latitude()));
		model.put("coordY", String.valueOf(posicion.longitude()));

		return new ModelAndView(model, "pois/editarPOI.hbs");
	}

	public ModelAndView borrarPOI(Request req, Response res) {

		return null;
	}

}
