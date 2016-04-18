package POI;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Comuna {
int numero;
Polygon territorio;
	
	public Boolean incluyeA(Point unaPosicion){
		return territorio.isInside(unaPosicion);
	}
}
