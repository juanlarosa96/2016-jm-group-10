package eventosBusqueda;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class InteresadoEnBusquedas {	
	
	public abstract void notificarBusqueda(ResultadoBusqueda unaBusqueda);
	
}
