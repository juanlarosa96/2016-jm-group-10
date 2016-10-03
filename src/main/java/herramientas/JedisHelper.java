package herramientas;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import pois.Banco;
import pois.POI;
import redis.clients.jedis.Jedis;

public class JedisHelper {

	private static Jedis jedis = new Jedis("127.0.0.1", 6379);
		

	public static void persistirPoiExterno(POI unPoi) {
	
		conectarARedis();
		String gsonAPersistir = convertirDePOIAJson(unPoi);
		jedis.lpush("IdPoiExterno", gsonAPersistir);
	
	}

	public static String convertirDePOIAJson(POI unPoi) {
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(unPoi);
		jsonElement.getAsJsonObject().remove("horarios");
		String gsonConvertido = gson.toJson(jsonElement);
		return gsonConvertido;
		
	}

	public static List<POI> obtenerPoisExternosDeRedis() {
		conectarARedis();
		List<POI> listaBancos = new ArrayList<POI>();
		List<String> bancosObtenidos = new ArrayList<String>();
		
		bancosObtenidos.addAll(jedis.lrange("IdPoiExterno", 0, jedis.llen("IdPoiExterno")));

		for (int i = 0; i < bancosObtenidos.size(); i++) {

			listaBancos.add(convertirDeJsonAPOI(bancosObtenidos.get(i)));

		}
		
		return listaBancos;
	}

	public static POI convertirDeJsonAPOI(String unPoi) {

		Gson gson = new Gson();
		
		Banco unBanco = gson.fromJson(unPoi, Banco.class);
		unBanco.setHorarios(unBanco.horariosBancos());
		return unBanco;

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
		//HACER EL POP Y PUSH

	}
	public static void limpiarBaseDeDatosRedis(){
		jedis.del("IdPoiExternos");
		
	}
	public static Long obtenerCantidadPersistida(){
		return jedis.llen("IdPoiExterno");
	}

}
