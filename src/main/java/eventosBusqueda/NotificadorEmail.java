package eventosBusqueda;

import adapters.AdapterMail;

public class NotificadorEmail implements InteresadoEnBusquedas {

	Double demoraMaximaEnSegundos;
	String emailAdmin;
	private AdapterMail adapterMail;

	public NotificadorEmail(Double demoraMaximaEnSegundos, String emailAdmin, AdapterMail unAdapter) {
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
		this.emailAdmin = emailAdmin;
		this.adapterMail = unAdapter;
	}

	@Override
	public void notificarBusqueda(ResultadoBusqueda unaBusqueda) {

		if (this.deboNotificarBusqueda(unaBusqueda)) {
			this.notificarBusquedaPorMail(unaBusqueda);
		}
	}

	private void notificarBusquedaPorMail(ResultadoBusqueda unaBusqueda) {
		adapterMail.enviarMailPorBusquedaLenta(unaBusqueda, emailAdmin);

	}

	private boolean deboNotificarBusqueda(ResultadoBusqueda unaBusqueda) {

		return unaBusqueda.getDemoraEnSegundos() > this.demoraMaximaEnSegundos;
	}

	public void setDemoraMaximaEnSegundos(Double demoraMaximaEnSegundos) {
		this.demoraMaximaEnSegundos = demoraMaximaEnSegundos;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

}
