package procesos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import org.joda.time.DateTime;

import pois.ManejadorDePois;

public class AccionActualizarLocalesComerciales implements Accion {

	private String rutaArchivo;
	private ManejadorDePois manejedorPois;

	@Override
	public ResultadoEjecucion ejecutar() throws ExceptionErrorEjecucionDeAccion {

		String linea;
		BufferedReader lectorArchivo = null;

		try {
			lectorArchivo = new BufferedReader(new FileReader(rutaArchivo));

			Integer cantElementosAfectados = 0;

			while ((linea = lectorArchivo.readLine()) != null) {
				String[] lineaComercio = linea.split(";", 2);
				String[] etiquetas = lineaComercio[1].split(" ");
				cantElementosAfectados += manejedorPois.actualizarEtiquetasLocalesComerciales(lineaComercio[0],
						Arrays.asList(etiquetas));
			}

			lectorArchivo.close();
			
			return ResultadoEjecucion.dameResultadoConCantElemAfectadosYMensaje(cantElementosAfectados,"Locales Comerciales Actualizados");

		} catch (Exception e) {
			throw new ExceptionErrorEjecucionDeAccion();
		}

	}

	public AccionActualizarLocalesComerciales(String ruta) {
		this.rutaArchivo = ruta;
		this.manejedorPois = ManejadorDePois.getInstance();
	}

}
