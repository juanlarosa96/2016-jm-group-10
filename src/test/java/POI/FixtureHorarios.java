package POI;

import org.joda.time.DateTime;

public class FixtureHorarios {
	
	public DateTime dameUnHorarioBancario() {
		return new DateTime(2016, 4, 4, 10, 0);
	}
	
	public DateTime dameUnHorarioNoBancario() {
		return new DateTime(2016, 4, 5, 2, 30);
	}
	
	public DateTime dameUnHorarioBancarioALaHoraDeCierre(){
		return new DateTime(2016, 5, 20, 15, 00, 0);
	}
}
