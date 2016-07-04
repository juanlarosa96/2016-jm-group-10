package procesos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import pois.ManejadorDePois;

public class AccionActualizarLocalesComerciales implements Accion {

	private String rutaArchivo;
	private ManejadorDePois manejedorPois;
	
	@Override
	public void ejecutar() throws Exception {

		String linea;
		BufferedReader lectorArchivo = null;

		try {
			lectorArchivo = new BufferedReader(new FileReader(rutaArchivo));
			while ((linea = lectorArchivo.readLine()) != null) {
				String[] lineaComercio = linea.split(";", 2);
				String[] etiquetas = lineaComercio[1].split(" ");
				manejedorPois.actualizarEtiquetasLocalesComerciales(lineaComercio[0], Arrays.asList(etiquetas));
			}

		}
		catch(Exception e){
				throw new Exception();		
		}
	}

	public AccionActualizarLocalesComerciales(String ruta) {
		this.rutaArchivo = ruta;
		this.manejedorPois = ManejadorDePois.getInstance();
	}

}
