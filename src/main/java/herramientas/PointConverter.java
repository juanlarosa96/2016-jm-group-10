package herramientas;

import javax.persistence.AttributeConverter;
import org.uqbar.geodds.Point;

public class PointConverter implements AttributeConverter<Point, String> {

	@Override
	public String convertToDatabaseColumn(Point point) {		
		return Double.toString(point.latitude()).concat(";").concat(Double.toString(point.longitude()));  
	}

	@Override
	public Point convertToEntityAttribute(String stringPoint) {
		String[] string = stringPoint.split(";", 2);
		return new Point(Double.parseDouble(string[0]), Double.parseDouble(string[1]));	
		
	}

}
