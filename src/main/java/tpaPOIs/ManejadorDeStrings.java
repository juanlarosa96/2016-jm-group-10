package tpaPOIs;

import java.util.Arrays;
import java.util.List;

public class ManejadorDeStrings {

	public static Boolean estaIncluido(String string1, String string2) {
		
		List<String> palabrasDeString2 = Arrays.asList(string2.split(" "));
		
		return palabrasDeString2.stream().anyMatch(palabra -> palabra.equalsIgnoreCase(string1));
		
	}
}
