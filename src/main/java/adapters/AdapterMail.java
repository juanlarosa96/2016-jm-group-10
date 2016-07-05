package adapters;

import eventosBusqueda.Busqueda;
import procesos.Proceso;

public interface AdapterMail {

	public void enviarMailPorBusquedaLenta(Busqueda unaBusqueda, String direccionEmailDestino);

	public void enviarMailPorErrorDeProceso(Proceso proceso, String direccionEmailDestino);

}
