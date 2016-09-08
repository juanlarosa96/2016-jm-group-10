package pois;

import javax.persistence.*;

@Table(name = "direcciones")
@Entity
public class Direccion {
	
	@Id @GeneratedValue
	private Integer id;
	
	private String calle;
	private Integer altura;
	private String entreCalle1;
	private String entreCalle2;
	private Integer piso;
	private Character departamento;
	private Integer codigoPostal;
	
	@SuppressWarnings("unused")
	private Direccion(){}
	
	public String getEntreCalle1() {
		return entreCalle1;
	}

	public void setEntreCalle1(String entreCalle1) {
		this.entreCalle1 = entreCalle1;
	}

	public String getEntreCalle2() {
		return entreCalle2;
	}

	public void setEntreCalle2(String entreCalle2) {
		this.entreCalle2 = entreCalle2;
	}

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public Character getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Character departamento) {
		this.departamento = departamento;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

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
		return (otraDireccion.getCalle().equalsIgnoreCase(this.calle)) && (otraDireccion.getAltura().equals(this.altura));
		
	}
}
