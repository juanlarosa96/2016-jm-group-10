package pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.joda.time.LocalTime;
import org.joda.time.DateTimeConstants;

@Table (name = "bancos")
@Entity
public class Banco extends POI {

	public Banco(Posicion posicion, String nombre, Direccion direccion, List<String> etiquetas) {
		this.setPosicion(posicion);
		this.setNombre(nombre);
		this.setHorarios(new ArrayList<FranjaHoraria>() {
			{
				add(new FranjaHoraria(DateTimeConstants.MONDAY, new LocalTime(9, 00), new LocalTime(15, 00)));
				add(new FranjaHoraria(DateTimeConstants.TUESDAY, new LocalTime(9, 00), new LocalTime(15, 00)));
				add(new FranjaHoraria(DateTimeConstants.WEDNESDAY, new LocalTime(9, 00), new LocalTime(15, 00)));
				add(new FranjaHoraria(DateTimeConstants.THURSDAY, new LocalTime(9, 00), new LocalTime(15, 00)));
				add(new FranjaHoraria(DateTimeConstants.FRIDAY, new LocalTime(9, 00), new LocalTime(15, 00)));
				
			}
		});
		this.setDireccion(direccion);
		this.setEtiquetas(etiquetas);
	}
	
	@SuppressWarnings("unused")
	private Banco(){}
	
	@Override
	protected void agregarDatosEspecificosDelPOI(POIDTO poiDto) {
		poiDto.setTipoPOI("BANCO");
	}
		
}
