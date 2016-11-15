package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import eventosBusqueda.ResultadoBusqueda;
import herramientas.PersistidorMongo;
import pois.Direccion;
import pois.ManejadorDePois;
import pois.POI;
import pois.Posicion;
import poisBusqueda.POIDTO;
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
		
		model.put("id", id);

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

	public ModelAndView guardarPOI(Request req, Response res){
		String idString = req.params("id");
		Long id = Long.parseLong(idString);
		POI poi = ManejadorDePois.getInstance().getPOI(id);
		
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
		String calle = valores.get(1);
		String altura = valores.get(2);
		String piso = valores.get(3);
		String depto = valores.get(4);
		String entreCalle1 = valores.get(5);
		String entreCalle2 = valores.get(6);
		String codPostal = valores.get(7);
		String localidad = valores.get(8);
		String barrio = valores.get(9);
		String provincia = valores.get(10);
		String pais = valores.get(11);
		
		
		if(!nombre.equals(" "))
			poi.setNombre(nombre);
		
		Direccion direccion = poi.getDireccion();
		
		if(!calle.equals(" "))
			direccion.setCalle(calle);
		
		if(!altura.equals(" "))
			direccion.setAltura(Integer.parseInt(altura));
		
		if(!piso.equals(" "))
			direccion.setPiso(Integer.parseInt(piso));
		
		if(!depto.equals(" "))
			direccion.setDepartamento(depto.charAt(0));
		
		if(!entreCalle1.equals(" "))
			direccion.setEntreCalle1(entreCalle1);
		
		if(!entreCalle2.equals(" "))
			direccion.setEntreCalle2(entreCalle2);
		
		if(!codPostal.equals(" "))
			direccion.setCodigoPostal(Integer.parseInt(codPostal));
		
		if(!localidad.equals(" "))
			direccion.setLocalidad(localidad);
		
		if(!barrio.equals(" "))
			direccion.setBarrio(barrio);
		
		if(!provincia.equals(" "))
			direccion.setProvincia(provincia);
		
		if(!pais.equals(" "))
			direccion.setPais(pais);
		
		poi.setDireccion(direccion);
		
		ManejadorDePois.getInstance().agregarPoiInterno(poi);
		
		return this.administrarPois(req, res);
		
	}

	public ModelAndView borrarPOI(Request req, Response res) {

		Long id = Long.parseLong(req.params("id"));
		
		POI poi = ManejadorDePois.getInstance().getPOI(id);
		
		ManejadorDePois.getInstance().eliminarPOIInterno(poi);
		
		return this.administrarPois(req, res);
	}
	
	public ModelAndView verPoisDeConsulta(Request req, Response res){
		
		String idConsulta = req.params("id");	
		
		PersistidorMongo persistidorMongo = new PersistidorMongo();
		persistidorMongo.inicializarDB("tpaPOIs");
		
		ResultadoBusqueda consulta = persistidorMongo.obtenerResultadoBusqueda(idConsulta);
		
		List<POIDTO> poisDTO = consulta.getPoisEncontrados();
		
		HashMap<String,List<POIDTO>> model = new HashMap<>();
		
		model.put("pois", poisDTO);
		
		return new ModelAndView(model, "pois/verPoisDeConsulta.hbs");
		
	}

}
