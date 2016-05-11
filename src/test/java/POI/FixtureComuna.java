package POI;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class FixtureComuna {
		
	public FixtureComuna(){
	}
	
	static Point damePosicionIncluida(){
		return new Point(-34.6327475, -58.4851585);
	}
	static Point damePosicionNoIncluida(){
		return new Point(-34.6184994, -58.4368164);
	}
	
	private static List<Point> limitesComuna= new ArrayList<Point>() {
		{
			add(new Point(-34.611015, -58.529025));
			add(new Point(-34.615256, -58.531221));
			add(new Point(-34.634217, -58.529806));
			add(new Point(-34.634495, -58.510883));
			add(new Point(-34.639959, -58.509675));
			add(new Point(-34.645743, -58.502325));
			add(new Point(-34.643387, -58.497065));
			add(new Point(-34.644764, -58.495284));
			add(new Point(-34.636925, -58.478568));
			add(new Point(-34.638797, -58.476058));
			add(new Point(-34.636537, -58.471423));
			add(new Point(-34.622482, -58.477860));
			add(new Point(-34.624637, -58.482710));
			add(new Point(-34.614536, -58.495627));
			add(new Point(-34.609238, -58.500219));
			add(new Point(-34.616726, -58.513008));
			add(new Point(-34.620399, -58.516870));
		}
	};
	public static Comuna dameComunaValida(){
		return new Comuna(10, limitesComuna);
	}
}

