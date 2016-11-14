package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import herramientas.EntityManagerHelper;
import pois.Dispositivo;
import pois.ExceptionComunaInvalida;
import pois.ManejadorDeDispositivos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalesController {

	public ModelAndView administrarTerminales(Request req, Response res) throws Exception, ExceptionComunaInvalida {
		Map<String, List<Dispositivo>> model = new HashMap<>();
		
		String comuna = req.queryParams("comuna");
		List<Dispositivo> terminales;

		if (comuna == null || comuna.equals("Todas")) {
			terminales = ManejadorDeDispositivos.getInstance().getListaDispositivos();
			
		} else {
			terminales = ManejadorDeDispositivos.getInstance().filtrarPorComuna(Integer.parseInt(comuna));
		}

		model.put("terminales", terminales);
		return new ModelAndView(model, "admin/administrarTerminales.hbs");
	}

	public ModelAndView eliminarTerminal(Request req, Response res) throws ExceptionComunaInvalida, Exception {
		Integer id = Integer.parseInt(req.params("id"));
		Dispositivo dispositivo = EntityManagerHelper.find(Dispositivo.class, id);

		ManejadorDeDispositivos.getInstance().eliminarDispositivo(dispositivo);

		return this.administrarTerminales(req, res);
	}
	
	public ModelAndView agregarTerminal(Request req, Response res) {
		Map<String, String> model = new HashMap<>();

		return new ModelAndView(model, "terminal/agregarTerminal.hbs");
	}

}
