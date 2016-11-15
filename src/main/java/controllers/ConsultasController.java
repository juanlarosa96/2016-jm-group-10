package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import eventosBusqueda.ResultadoBusqueda;
import herramientas.PersistidorMongo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultasController {

	public ModelAndView historicoConsultas(Request req, Response res) {

		String fecDesde = req.queryParams("fechaDesde");
		String fecHasta = req.queryParams("fechaHasta");
		String nombreTerminal = req.queryParams("terminal");
		String condicionCantPois = req.queryParams("condicionCantPois");
		String cantPois=req.queryParams("cantPois");

		HashMap<String, List<ResultadoBusqueda>> model = new HashMap<>();

		PersistidorMongo persistidorMongo = new PersistidorMongo();
		persistidorMongo.inicializarDB("tpaPOIs");

		List<ResultadoBusqueda> resultadosBusquedas = persistidorMongo.obtenerTodosLosResultadosBusqueda();

		if (fecDesde!=null && !fecDesde.isEmpty() && fecHasta != null && !fecHasta.isEmpty()) {
			DateTime fechaDesde = DateTime.parse(fecDesde);
			DateTime fechaHasta = DateTime.parse(fecHasta);

			resultadosBusquedas = resultadosBusquedas.stream()
					.filter(busq -> busq.getFecha().isBefore(fechaHasta.getMillis())
							&& busq.getFecha().isAfter(fechaDesde.getMillis()))
					.collect(Collectors.toList());
		}
		
		if(nombreTerminal!=null && !nombreTerminal.isEmpty() && !nombreTerminal.equals("Todas"))
			resultadosBusquedas = resultadosBusquedas.stream()
			.filter(busq -> busq.getNombreTerminal().equals(nombreTerminal))
			.collect(Collectors.toList());
		
		if(condicionCantPois!=null && !condicionCantPois.equals("Cualquiera") && cantPois!=null & !cantPois.isEmpty()){
			
			Integer cantDePois = Integer.parseInt(cantPois);
			
			if(condicionCantPois.equals("Menor a")){
				resultadosBusquedas = resultadosBusquedas.stream()
						.filter(busq -> busq.getCantResultados() < cantDePois)
						.collect(Collectors.toList());
			}
			
			else if(condicionCantPois.equals("Menor o igual a")){
				resultadosBusquedas = resultadosBusquedas.stream()
						.filter(busq -> busq.getCantResultados() <= cantDePois)
						.collect(Collectors.toList());
			}
			
			else if(condicionCantPois.equals("Igual a")){
				resultadosBusquedas = resultadosBusquedas.stream()
						.filter(busq -> busq.getCantResultados() == cantDePois)
						.collect(Collectors.toList());
			}
			
			else if(condicionCantPois.equals("Mayor o igual a")){
				resultadosBusquedas = resultadosBusquedas.stream()
						.filter(busq -> busq.getCantResultados() >= cantDePois)
						.collect(Collectors.toList());
			}
			
			else if(condicionCantPois.equals("Mayor a")){
				resultadosBusquedas = resultadosBusquedas.stream()
						.filter(busq -> busq.getCantResultados() > cantDePois)
						.collect(Collectors.toList());
			}
		}		
		
		model.put("consultas", resultadosBusquedas);

		return new ModelAndView(model, "admin/historicoConsultas.hbs");
	}

}
