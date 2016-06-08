package fixtures;

import java.util.ArrayList;
import java.util.List;

import adapters.CentroDTO;
import adapters.RangoServicioDTO;
import adapters.ServicioDTO;

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
			add(new RangoServicioDTO(1, 9, 30, 14, 0));
			add(new RangoServicioDTO(2, 9, 30, 14, 0));
			add(new RangoServicioDTO(3, 9, 30, 14, 0));
			add(new RangoServicioDTO(4, 9, 30, 14, 0));
			add(new RangoServicioDTO(5, 9, 30, 14, 0));
		}
	};
	
	private static List<RangoServicioDTO> rangosHorariosAsesoramientoJuridico = new ArrayList<RangoServicioDTO>(){
		{
			add(new RangoServicioDTO(2,9,0,16,0));
		}
	};
	
	private static List<RangoServicioDTO> rangosHorariosEcobici = new ArrayList<RangoServicioDTO>(){
		{
			add(new RangoServicioDTO(1,10,0,15,0));
			add(new RangoServicioDTO(2,10,0,15,0));
			add(new RangoServicioDTO(3,10,0,15,0));
			add(new RangoServicioDTO(4,10,0,15,0));
			add(new RangoServicioDTO(5,10,0,15,0));
		}
	};
	
	private static List<ServicioDTO> serviciosDTO1 = new ArrayList<ServicioDTO>(){
		{
			add(new ServicioDTO("atencion ciudadana", rangosHorariosAtencionCiudadana));
			add(new ServicioDTO("rentas", rangosHorariosRentas));
		}
	};
	
	
	private static List<ServicioDTO> serviciosDTO2 = new ArrayList<ServicioDTO>(){
		

		{	add(new ServicioDTO("rentas", rangosHorariosRentas));
			add(new ServicioDTO("asesoramiento juridico", rangosHorariosAsesoramientoJuridico));
			add(new ServicioDTO("ecobici", rangosHorariosEcobici));
		}
	}; 
		
	
	public static CentroDTO dameCentroDTO1() {
		return new CentroDTO(3, "balvanera, san cristobal", "Zlatan Ibrahimovic", "Junin 521", "4375-0644/45", serviciosDTO1);
	}
	
	public static CentroDTO dameCentroDTOparecidoACgpValido() {
		return new CentroDTO(10, "monte castro, floresta", "Nestor Ortigoza", "Bacacay 3968", "0800-999-2727", serviciosDTO2);
	}
	
	
}
