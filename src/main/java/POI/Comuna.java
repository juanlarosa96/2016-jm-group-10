package POI;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Comuna {
	int numero;
	Polygon territorio;

	public Comuna(int unNumero, List<Point> puntosFrontera) {
		numero = unNumero;
		territorio=new Polygon(puntosFrontera);
	}

	public Boolean incluyeA(Point unaPosicion) {
		return territorio.isInside(unaPosicion);
	}

}
