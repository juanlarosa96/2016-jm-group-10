package pois;

import java.util.List;

import javax.persistence.*;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

@Embeddable
public class Comuna {	
	
	private Integer numero;
	
	//Como convertir polygon?
	private Polygon territorio;
	
	//-----------------------
	
	@SuppressWarnings("unused")
	private Comuna(){}

	public Comuna(Integer unNumero, List<Point> puntosFrontera) {
		numero = unNumero;
		territorio = new Polygon(puntosFrontera);
	}
	
	public Polygon getTerritorio() {
		return territorio;
	}

	public void setTerritorio(Polygon territorio) {
		this.territorio = territorio;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean incluyeA(Point unaPosicion) {
		return territorio.isInside(unaPosicion);
	}

	public Integer getNumero() {
		return numero;
	}

}
