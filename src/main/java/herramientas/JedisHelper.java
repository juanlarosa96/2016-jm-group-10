package herramientas;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import converters.JsonPoiConverter;
import pois.POI;
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

	public static List<POI> buscarPoisEnRedis(String descripcion) {

		conectarARedis();
		POI poiBuscado;
		List<POI> listaPoisEncontrados = new ArrayList<POI>();
		Long largo = jedis.llen("IdPoiExterno");
		
		for (int i = 0; i < largo; i++) {
			poiBuscado = JsonPoiConverter.convertirDeJsonAPOI(jedis.lpop("IdPoiExterno"));
			if (poiBuscado.contiene(descripcion)) {
				persistirPoiExterno(poiBuscado);
				listaPoisEncontrados.add(poiBuscado);
			}
		}
		return listaPoisEncontrados;

	}

	public static void limpiarBaseDeDatosRedis() {
		jedis.flushAll();

	}

	public static Long obtenerCantidadPersistida() {
		return jedis.llen("IdPoiExterno");
	}

}
