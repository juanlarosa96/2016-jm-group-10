package herramientas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import converters.JsonPoiConverter;
import pois.Banco;
import pois.CGP;
import pois.FranjaHoraria;
import pois.POI;
import pois.Servicio;
import redis.clients.jedis.Jedis;

public class JedisHelper {

	private static Jedis jedis = new Jedis("127.0.0.1", 6379);

	public static void persistirPoiExterno(POI unPoi) {

		conectarARedis();
		JsonObject gsonAPersistir = JsonPoiConverter.convertirDePOIAJson(unPoi);
		jedis.rpush("IdPoiExterno", gsonAPersistir.toString());

	}

	public static List<POI> obtenerPoisExternosDeRedis() {
		conectarARedis();
		List<POI> listaPOI = new ArrayList<POI>();
		List<String> poisObtenidos = new ArrayList<String>();

		poisObtenidos.addAll(jedis.lrange("IdPoiExterno", 0, jedis.llen("IdPoiExterno")));

		for (int i = 0; i < poisObtenidos.size(); i++) {

			listaPOI.add(JsonPoiConverter.convertirDeJsonAPOI(poisObtenidos.get(i)));

		}

		return listaPOI;
	}

	public static void conectarARedis() {

		if (!jedis.isConnected()) {
			jedis.connect();
		}

	}

	public static void desconectarRedis() {

		if (jedis.isConnected()) {
			jedis.disconnect();
		}
	}

	public static void actualizarPoiExterno(POI poiExternoNuevo, POI poiViejoLista) {

		conectarARedis();
		String poiViejo = JsonPoiConverter.convertirDePOIAJson(poiViejoLista).toString();
		String poiNuevo = JsonPoiConverter.convertirDePOIAJson(poiExternoNuevo).toString();
		jedis.lrem("IdPoiExterno", 1, poiViejo);
		jedis.rpush("IdPoiExterno", poiNuevo);

	}

	public static POI buscarUnPoiEnRedis(String descripcion) {
		conectarARedis();
		POI poiBuscado;
		for (int i = 0; i < jedis.llen("IdPoiExterno"); i++) {
			poiBuscado = JsonPoiConverter.convertirDeJsonAPOI(jedis.lpop("IdPoiExterno"));
			if (poiBuscado.contiene(descripcion)) {
				persistirPoiExterno(poiBuscado);
				return poiBuscado;
			}
		}
		return null;

	}

	public static void limpiarBaseDeDatosRedis() {
		jedis.flushAll();

	}

	public static Long obtenerCantidadPersistida() {
		return jedis.llen("IdPoiExterno");
	}

}
