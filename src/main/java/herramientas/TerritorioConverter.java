package herramientas;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeConverter;
import org.uqbar.geodds.Point;
import pois.Territorio;

public class TerritorioConverter implements AttributeConverter<Territorio, String> {

	private PointConverter pointConverter;

	@Override
	public String convertToDatabaseColumn(Territorio territorio) {
		String stringTerritorio= "";
		List<Point> surface = territorio.getSurface();
		surface.stream().forEach(point -> stringTerritorio.concat(pointConverter.convertToDatabaseColumn(point).concat("|")));	
		return stringTerritorio;

	}
	
	@Override
	public Territorio convertToEntityAttribute(String stringTerritorio) {
		
		List<Point> points = new ArrayList<Point>();
		String[] stringPoints = stringTerritorio.split("|");

		for (int i = 0; i < stringPoints.length; i++) {
			points.add(pointConverter.convertToEntityAttribute(stringPoints[i]));
		}
		
		return new Territorio(points);

	}

}