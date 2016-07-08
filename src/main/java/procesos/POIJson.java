package procesos;

public class POIJson {
	
	private String Nombre;
	private String Calle;
	private Integer Altura;
	//private DateTime FechaBaja;
	
public POIJson (){
		
		
	} 

public POIJson(String Nombre, String Calle, Integer Altura /* ,DateTime FechaBaja*/) {
	this.setNombre(Nombre);
	this.setCalle(Calle);
	this.setAltura(Altura);
	//this.setFechaBaja(FechaBaja);
	
}

public String getNombre() {
	return Nombre;
}

public void setNombre(String nombre) {
	Nombre = nombre;
}

public String getCalle() {
	return Calle;
}

public void setCalle(String calle) {
	Calle = calle;
}

public Integer getAltura() {
	return Altura;
}

public void setAltura(Integer altura) {
	Altura = altura;
}
	
/*public DateTime getFechaBaja() {
	return FechaBaja;
}*/

/*public void setFechaBaja(DateTime fechaBaja) {
	FechaBaja = fechaBaja;
}*/


}
