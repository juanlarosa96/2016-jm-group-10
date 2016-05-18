package tpaPOIs;

public class RangoServicioDTO {
	
	private Integer dia;
	private Integer horaDesde; 
	private Integer minutoDesde;
	private Integer horaHasta;
	private Integer minutoHasta;
	
	public RangoServicioDTO(Integer dia, Integer horaDesde, Integer minutoDesde, Integer horaHasta,
			Integer minutoHasta) {
		this.dia = dia;
		this.horaDesde = horaDesde;
		this.minutoDesde = minutoDesde;
		this.horaHasta = horaHasta;
		this.minutoHasta = minutoHasta;
	}
	
	public Integer getDia() {
		return dia;
	}
	public Integer getHoraDesde() {
		return horaDesde;
	}
	public Integer getMinutoDesde() {
		return minutoDesde;
	}
	public Integer getHoraHasta() {
		return horaHasta;
	}
	public Integer getMinutoHasta() {
		return minutoHasta;
	}
	


}
