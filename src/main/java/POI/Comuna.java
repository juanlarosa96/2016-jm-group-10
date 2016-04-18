package POI;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Comuna {
	int numero;
	Polygon territorio;

	public Comuna(int unNumero, List<Point> puntosFrontera) {
		numero = unNumero;
		puntosFrontera.stream().map(punto -> territorio.add(punto));
	}

	public Boolean incluyeA(Point unaPosicion) {
		return territorio.isInside(unaPosicion);
	}

}
