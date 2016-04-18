package POI;

public class Rentas implements Servicio {
String nombre;
	@Override
	public Boolean estaDisponible() {
	
		return false;
	}
    public Boolean nombreSimilarA(String unNombre){
    	return nombre.contains(unNombre);
    }
}
