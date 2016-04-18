package POI;

import org.joda.time.DateTime;

public class Banco extends POI {

	public boolean estaDiponibleEn(DateTime momento) {
		if (momento.getDayOfWeek() >= 1 && momento.getDayOfWeek()<= 5 && momento.getHourOfDay() >= 10 && momento.getHourOfDay() <= 15){
			return true;
		} else return false;
	}

}
