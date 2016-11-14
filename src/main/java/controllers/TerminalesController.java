package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import herramientas.EntityManagerHelper;
import pois.Dispositivo;
import pois.ExceptionComunaInvalida;
import pois.ManejadorDeDispositivos;
import pois.Posicion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalesController {

	public ModelAndView administrarTerminales(Request req, Response res) throws Exception, ExceptionComunaInvalida {
		String comuna = req.queryParams("comuna");
		List<Dispositivo> terminales = new ArrayList<Dispositivo>();

		if (comuna == null || comuna.equals("Todas")) {
			terminales = ManejadorDeDispositivos.getInstance().getListaDispositivos();
			Dispositivo dispositivo = new Dispositivo("Terminal 1", new Posicion(-34.622382, -58.511592));
			terminales.add(dispositivo);
			EntityManagerHelper.persistir(dispositivo);
		} else {
			terminales = ManejadorDeDispositivos.getInstance().filtrarPorComuna(Integer.parseInt(comuna));
		}

		Map<String, List<Dispositivo>> model = new HashMap<>();

		model.put("terminales", terminales);
		return new ModelAndView(model, "admin/administrarTerminales.hbs");
	}

	public ModelAndView eliminarTerminal(Request req, Response res) {
		Integer id = Integer.parseInt(req.params("id"));
		Dispositivo dispositivo = EntityManagerHelper.find(Dispositivo.class, id);

		ManejadorDeDispositivos.getInstance().eliminarDispositivo(dispositivo);
		
		List<Dispositivo> terminales = new ArrayList<>();
		terminales = ManejadorDeDispositivos.getInstance().getListaDispositivos();
		Map<String, List<Dispositivo>> model = new HashMap<>();

		model.put("terminales", terminales);
		return new ModelAndView(model, "admin/administrarTerminales.hbs");
	}

}
