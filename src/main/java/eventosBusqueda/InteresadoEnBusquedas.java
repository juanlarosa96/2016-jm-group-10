package eventosBusqueda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class InteresadoEnBusquedas {	
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	

	public abstract void notificarBusqueda(ResultadoBusqueda unaBusqueda);
	
	public abstract String getNombreAccion();
	
	public Integer getId() {
		return id;
	}
}
