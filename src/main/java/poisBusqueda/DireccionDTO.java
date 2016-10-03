package poisBusqueda;


public class DireccionDTO {
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

	@SuppressWarnings("unused")
	private DireccionDTO() {
	}

	public DireccionDTO(String calle, Integer altura, String entreCalle1, String entreCalle2, Integer piso,
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

	public String getEntreCalle1() {
		return entreCalle1;
	}

	public String getEntreCalle2() {
		return entreCalle2;
	}

	public Integer getPiso() {
		return piso;
	}

	public Character getDepartamento() {
		return departamento;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getBarrio() {
		return barrio;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getPais() {
		return pais;
	}
	
	

}
