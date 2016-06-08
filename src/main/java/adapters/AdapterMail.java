package adapters;

import eventosBusqueda.Busqueda;

public interface AdapterMail {

	public void enviarMailPorBusquedaLenta(Busqueda unaBusqueda, String direccionEmailDestino);

}
