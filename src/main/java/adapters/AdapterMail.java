package adapters;

import eventosBusqueda.ResultadoBusqueda;
import procesos.Proceso;

public interface AdapterMail {

	public void enviarMailPorBusquedaLenta(ResultadoBusqueda unaBusqueda, String direccionEmailDestino);

	public void enviarMailPorErrorDeProceso(Proceso proceso, String direccionEmailDestino);

}
