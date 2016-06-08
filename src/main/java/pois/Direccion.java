package pois;

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

	public String getCalle() {
		return calle;
	}

	public Integer getAltura() {
		return altura;
	}

	public void mostrarDireccion() {
		String direccion;

		direccion = this.calle + " " + this.altura;

		if (this.piso != null) {
			direccion += "\n" + "Piso " + this.piso.toString();

			if (this.departamento != null)
				direccion += " Departamento " + this.departamento.toString();
		}

		direccion += "\n" + "Entre " + entreCalle1 + " y " + entreCalle2;

		direccion += "\n" + "CP: " + this.codigoPostal.toString() + " " + this.localidad + " " + this.barrio;

		direccion += "\n" + this.provincia + " " + this.pais + "\n";

		System.out.println(direccion);
	}

	public static void main(String args[]) {

		// direccion sin piso ni depto
		Direccion dir1 = new Direccion("Guayaquil", 25, "Av La Plata", "Senillosa", null, null, 1424, "CABA",
				"Caballito", "Buenos Aires", "Argentina");

		// direccion con piso y depto
		Direccion dir2 = new Direccion("Guayaquil", 25, "Av La Plata", "Senillosa", 1, 'A', 1424, "CABA", "Caballito",
				"Buenos Aires", "Argentina");

		dir1.mostrarDireccion();

		dir2.mostrarDireccion();

	}

	public Boolean esLaMismaDireccionQue(Direccion otraDireccion) {		
		return (otraDireccion.getCalle()==this.calle) && (otraDireccion.getAltura()==this.altura);
	}
}
