package pois;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

@Embeddable
public class Comuna {

	private Integer numero;

	@Embedded
	private Territorio territorio;

	// -----------------------

	@SuppressWarnings("unused")
	private Comuna() {
	}

	public Comuna(Integer unNumero, List<Posicion> puntosFrontera) {
		numero = unNumero;
		territorio = new Territorio(puntosFrontera);
	}

	public Comuna clone() {
		return new Comuna(numero, this.clonarPuntos(territorio.getSurface()));
	}

	private List<Posicion> clonarPuntos(List<Posicion> puntos) {
		return puntos.stream().map(punto -> punto.clone()).collect(Collectors.toList());
	}

	public Territorio getTerritorio() {
		return territorio;
	}

	public void setTerritorio(Territorio territorio) {
		this.territorio = territorio;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean incluyeA(Posicion unaPosicion) {
		return territorio.isInside(unaPosicion);
	}

	public Integer getNumero() {
		return numero;
	}

}
