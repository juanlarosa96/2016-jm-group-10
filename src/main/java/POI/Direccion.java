package POI;

public class Direccion {
	private String calle;
	private Integer altura;
	private Integer piso;
	private Character departamento;
	
	public Direccion(String unaCalle, Integer unaAltura, Integer unPiso, Character unDepartamento ){
		this.calle = unaCalle;
		this.altura = unaAltura;
		this.piso = unPiso;
		this.departamento = unDepartamento;
		
		
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
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
	
}
