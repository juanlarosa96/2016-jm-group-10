package pois;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Comuna {
	private Integer numero;
	private Polygon territorio;

	public Comuna(Integer unNumero, List<Point> puntosFrontera) {
		numero = unNumero;
		territorio = new Polygon(puntosFrontera);
	}

	public Boolean incluyeA(Point unaPosicion) {
		return territorio.isInside(unaPosicion);
	}

	public Integer getNumero() {
		return numero;
	}

}
