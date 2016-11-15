package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	
	public ModelAndView agregarNuevaTerminal(Request req, Response res) throws ExceptionComunaInvalida, Exception {
		String body = req.body();
		String[] atributos = body.split("&");
		Integer cantAtributos = atributos.length;
		
		List<String> valores = new ArrayList<String>();
		String valor;
		
		for(int i=0; i < cantAtributos; i++){
			if(atributos[i].split("=").length > 1)
				valor = atributos[i].split("=")[1];
			else
				valor = " ";
			
			valores.add(valor);
		}
		
		valores = valores.stream().map(unValor -> unValor.replace("+", " ")).collect(Collectors.toList());
		
		String nombre = valores.get(0);
		String latitud = valores.get(1);
		String longitud = valores.get(2);
		
		Posicion posicion = new Posicion(Double.parseDouble(latitud), Double.parseDouble(longitud));
		Dispositivo nuevoDispositivo = new Dispositivo(nombre, posicion);
		
		ManejadorDeDispositivos.getInstance().agregarDispositivo(nuevoDispositivo);

		return this.administrarTerminales(req, res);
	}

}
