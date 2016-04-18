package POI;

public class Direccion {
	private String calle;
	private Integer altura;
	private String entreCalle1;
	private String entreCalle2;
	private Integer piso;
	private Character departamento;
	private Integer codigoPostal;
	private String localidad;
	private String barrio;
	private String provincia;
	private String pais;

	public Direccion(String calle, Integer altura, String entreCalle1, String entreCalle2, Integer piso,
			Character departamento, Integer codigoPostal, String localidad, String barrio, String provincia,
			String pais) {
		this.calle = calle;
		this.altura = altura;
		this.entreCalle1 = entreCalle1;
		this.entreCalle2 = entreCalle2;
		this.piso = piso;
		this.departamento = departamento;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.barrio = barrio;
		this.provincia = provincia;
		this.pais = pais;
	}

	public void mostrarDireccion() {
		String direccion;

		direccion = this.calle + " " + this.altura;

		if (this.piso != null)
		{
			direccion += "\n" + "piso " + this.piso.toString();

			if (this.departamento != null)
				direccion += " departamento " + this.departamento.toString();
		}
		
		direccion+= "\n" + "entre " + entreCalle1 + "y " + entreCalle2;
		
		
		
		
		

		System.out.println(direccion);
	}

}
