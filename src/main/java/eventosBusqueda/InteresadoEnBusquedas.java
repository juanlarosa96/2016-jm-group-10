package eventosBusqueda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InteresadoEnBusquedas {	
	
	@Id @GeneratedValue
	private Integer id;
	
	public abstract void notificarBusqueda(ResultadoBusqueda unaBusqueda);
	
}
