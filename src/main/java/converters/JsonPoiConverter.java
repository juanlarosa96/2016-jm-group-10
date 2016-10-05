package converters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import herramientas.ManejadorDeFechas;
import pois.Banco;
import pois.CGP;
import pois.FranjaHoraria;
import pois.POI;
import pois.Servicio;

public class JsonPoiConverter {

	private static Gson gson = new Gson();
	private static JsonParser parser = new JsonParser();

	public static JsonObject convertirDePOIAJson(POI unPoi) {

		if (unPoi.getClass().equals(Banco.class)) {
			JsonElement jsonElement = gson.toJsonTree(unPoi);
			jsonElement.getAsJsonObject().remove("horarios");
			jsonElement.getAsJsonObject().addProperty("tipoPoi", unPoi.getClass().getName());
			JsonObject bancoConvertido = gson.toJsonTree(jsonElement).getAsJsonObject();
			return bancoConvertido;
		}

		CGP cgpAConvertir = (CGP) unPoi;
		List<Servicio> listaServicios = cgpAConvertir.getServicios();
		List<JsonObject> serviciosConvertidos = listaServicios.stream().map(s -> convertirServiciosAJson(s))
				.collect(Collectors.toList());
		JsonObject cgpJson = gson.toJsonTree(cgpAConvertir).getAsJsonObject();
		cgpJson.addProperty("tipoPoi", unPoi.getClass().getName());
		cgpJson.remove("servicios");
		cgpJson.addProperty("servicios", gson.toJson(serviciosConvertidos));

		return cgpJson;
	}

	public static JsonObject convertirServiciosAJson(Servicio servicio) {

		List<FranjaHoraria> horariosDeServicio = servicio.getHorarios();

		JsonObject servicioParseado = parser.parse(gson.toJson(servicio)).getAsJsonObject();
		List<String> horariosJson = ManejadorDeFechas.convertirListaFranjaHorariaAListaString(horariosDeServicio);

		servicioParseado.remove("horarios");
		servicioParseado.add("horarios", gson.toJsonTree(horariosJson));
		return servicioParseado;
	}

	public static POI convertirDeJsonAPOI(String unPoi) {

		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(unPoi, JsonObject.class);
		Class clasePoi;

		String nombreClase = jsonObject.remove("tipoPoi").toString();
		nombreClase = nombreClase.substring(1, nombreClase.length() - 1);
		try {
			clasePoi = Class.forName(nombreClase);

			if (clasePoi == Banco.class) {
				Banco unBanco = gson.fromJson(unPoi, Banco.class);
				unBanco.setHorarios(unBanco.horariosBancos());
				return unBanco;
			} else {
				String servicios = jsonObject.remove("servicios").getAsString();
				JsonArray serviciosJson = gson.fromJson(servicios, JsonArray.class);
				List<Servicio> serviciosConvertidos = convertirServiciosJsonAServicios(serviciosJson);
				
				CGP unCgp = gson.fromJson(jsonObject, CGP.class);
				unCgp.setServicios(serviciosConvertidos);
				return unCgp;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static List<Servicio> convertirServiciosJsonAServicios(JsonArray serviciosJson) {
		List<Servicio> serviciosConvertidos = new ArrayList<Servicio>();
		for (int i = 0; i < serviciosJson.size(); i++) {
			serviciosConvertidos.add(ConvertirServicioJsonAServicio(serviciosJson.get(i).getAsJsonObject()));
		}
		return serviciosConvertidos;
	}

	private static Servicio ConvertirServicioJsonAServicio(JsonObject servicio) {

		JsonArray horariosServicioJson = gson.fromJson(servicio.remove("horarios"), JsonArray.class);
		List<FranjaHoraria> listaHorarios = new ArrayList<FranjaHoraria>();

		for (int i = 0; i < horariosServicioJson.size(); i++) {
			FranjaHoraria horarios = ManejadorDeFechas
					.convertirStringAFranjaHoraria(horariosServicioJson.get(i).getAsString());
			listaHorarios.add(horarios);

		}
		Servicio servicioConvertido = gson.fromJson(servicio, Servicio.class);
		servicioConvertido.setHorarios(listaHorarios);
		return servicioConvertido;
	}

}
