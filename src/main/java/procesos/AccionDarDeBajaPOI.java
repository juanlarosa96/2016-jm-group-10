package procesos;

import org.joda.time.DateTime;

import pois.Direccion;
import pois.ManejadorDePois;
import pois.POI;

public class AccionDarDeBajaPOI implements Accion {

	private ManejadorDePois manejadorDePois;
	private String nombrePOI;
	private Direccion direccionPOI;
	private DateTime fechaYhoraDeBaja;

	@Override
	public void ejecutar() {
		try{
		POI poiBuscado = manejadorDePois.buscarPOI(nombrePOI, direccionPOI);
		manejadorDePois.eliminarPOI(poiBuscado);
		}
		catch(Exception e){
			throw new Exception();
		}
		
	}

	public AccionDarDeBajaPOI(String nombrePOI, Direccion direccionPOI, DateTime fechaYhoraDeBaja) {

		this.manejadorDePois = ManejadorDePois.getInstance();
		this.nombrePOI = nombrePOI;
		this.direccionPOI = direccionPOI;
		this.fechaYhoraDeBaja = fechaYhoraDeBaja;

	}
}
