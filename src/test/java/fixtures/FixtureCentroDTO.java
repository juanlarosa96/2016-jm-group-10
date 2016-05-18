package fixtures;

import java.util.ArrayList;
import java.util.List;

import tpaPOIs.CentroDTO;
import tpaPOIs.RangoServicioDTO;
import tpaPOIs.ServicioDTO;

public class FixtureCentroDTO {

	private static List<RangoServicioDTO> rangosHorariosAtencionCiudadana = new ArrayList<RangoServicioDTO>(){
		{
			add(new RangoServicioDTO(1,9,0,18,0));
			add(new RangoServicioDTO(2,9,0,18,0));
			add(new RangoServicioDTO(3,9,0,18,0));
			add(new RangoServicioDTO(4,9,0,18,0));
			add(new RangoServicioDTO(5,9,0,18,0));
		}
	};
	
	private static List<RangoServicioDTO> rangosHorariosRentas = new ArrayList<RangoServicioDTO>(){
		{
			add(new RangoServicioDTO(1, 8, 0, 17, 0));
			add(new RangoServicioDTO(3, 8, 0, 17, 0));
			add(new RangoServicioDTO(5, 8, 0, 17, 0));
		}
	};
	
	private static List<RangoServicioDTO> rangosHorariosAsesoramientoJuridico = new ArrayList<RangoServicioDTO>(){
		{
			add(new RangoServicioDTO(2,10,30,15,30));
			add(new RangoServicioDTO(4,10,30,15,30));
		}
	};
	
	private static List<ServicioDTO> serviciosDTO1 = new ArrayList<ServicioDTO>(){
		{
			add(new ServicioDTO("atencion ciudadana", rangosHorariosAtencionCiudadana));
			add(new ServicioDTO("rentas", rangosHorariosRentas));
		}
	};

	private static List<ServicioDTO> serviciosDTO2 = new ArrayList<ServicioDTO>(){
		{
			add(new ServicioDTO("asesoramiento juridico", rangosHorariosAsesoramientoJuridico));
			add(new ServicioDTO("rentas", rangosHorariosRentas));
		}
	}; 
	
	private static List<ServicioDTO> serviciosDTO3 = new ArrayList<ServicioDTO>(){
		{
			add(new ServicioDTO("asesoramiento juridico", rangosHorariosAsesoramientoJuridico));
			add(new ServicioDTO("atencion ciudadana", rangosHorariosAtencionCiudadana));
		}
	}; 
	
	public static CentroDTO dameCentroDTO1() {
		return new CentroDTO(3, "balvanera, san cristobal", "Zlatan Ibrahimovic", "Junin 521", "4375-0644/45", serviciosDTO1);
	}
	
	public static CentroDTO dameCentroDTO2() {
		return new CentroDTO(5, "almagro, boedo", "Nestor Ortigoza", "Carlos Calvo 3307", "0800-999-2727", serviciosDTO2);
	}
	
	public static CentroDTO dameCentroDTO3() {
		return new CentroDTO(7, "flores", "Danny Drinkwater", "Av Rivadavia 7202", "0800-999-2727", serviciosDTO3);
	}
}
